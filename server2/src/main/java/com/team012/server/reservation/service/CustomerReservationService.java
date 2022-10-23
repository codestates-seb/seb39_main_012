package com.team012.server.reservation.service;


import com.team012.server.company.repository.CompanyRepository;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.service.PostsService;
import com.team012.server.reservation.dto.RegisterReservationDto;
import com.team012.server.reservation.dto.ReservationCreateDto;
import com.team012.server.reservation.dto.ReservationUserInfoDto;
import com.team012.server.reservation.entity.BookingInfo;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.reservation.helper.AvailableReservationHelper;
import com.team012.server.reservation.repository.ReservationRepository;
import com.team012.server.room.entity.Room;
import com.team012.server.room.service.RoomService;
import com.team012.server.users.entity.Users;
import com.team012.server.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerReservationService {
    //고객 기준
    private final ReservationRepository reservationRepository;
    private final UsersService usersService;
    private final RoomService roomService;
    private final PostsService postsService;
    private final AvailableReservationHelper availableReservationHelper;

    private final CompanyRepository companyRepository;

    // 상세 페이지에서 예약 내역 적는 부분(posts 상세 페이지 ---> 예약 상세 페이지)
    @Transactional(readOnly = true)
    public ReservationCreateDto registerReservation(RegisterReservationDto dto, Long userId, Long postsId) {
        usersService.findUsersById(userId); //validation check
        Long companyId = postsService.findById(postsId).getCompanyId();
        String companyName = companyRepository.findById(companyId).get().getCompanyName();
        validateDate(dto.getCheckInDate(), dto.getCheckOutDate());

        ReservationCreateDto reservationCreateDto = calculatePriceAndAvailableBooking(dto, postsId);

        reservationCreateDto.setCheckInDate(dto.getCheckInDate());
        reservationCreateDto.setCheckInTime(dto.getCheckInTime());
        reservationCreateDto.setCheckOutDate(dto.getCheckOutDate());
        reservationCreateDto.setCheckOutTime(dto.getCheckOutTime());
        reservationCreateDto.setCompanyName(companyName);

        return reservationCreateDto;

    }

    @Transactional(readOnly = true)
    public void validateDate(String strCheckIn, String strCheckOut) {
        LocalDate checkIn = LocalDate.parse(strCheckIn, DateTimeFormatter.ISO_DATE);
        LocalDate checkOut = LocalDate.parse(strCheckOut, DateTimeFormatter.ISO_DATE);

        //당일 예약 불가 및 하루 전날 예약 불가
        if (checkIn.isBefore(LocalDate.now().plusDays(1)) || checkOut.isBefore(LocalDate.now().plusDays(1))) {
            throw new RuntimeException("당일예약/오늘 이전 날짜 예약 불가");
        } else if (checkOut.isBefore(checkIn)) {
            throw new RuntimeException("체크아웃 날짜는 체크인보다 뒤에 있어야 한다");
        }
    }

    //돈 계산 및 에약 가능 여부 판단.
    @Transactional(readOnly = true)
    public ReservationCreateDto calculatePriceAndAvailableBooking(RegisterReservationDto dto, Long postsId) {

        LocalDate checkInDate = LocalDate.parse(dto.getCheckInDate(), DateTimeFormatter.ISO_DATE);
        LocalDate checkOutDate = LocalDate.parse(dto.getCheckOutDate(), DateTimeFormatter.ISO_DATE).minusDays(1);

        int price = 0;
        Integer totalCount = 0;
        Integer count;
        Set<String> set = dto.getMap().keySet();
        List<BookingInfo> bookingInfos = new ArrayList<>();

        Posts posts = postsService.findById(postsId);
        Long companyId = posts.getCompanyId();

        List<Reservation> reservations = reservationRepository.findByCheckInCheckOut(companyId);
        for (String s : set) { //예약할 마리 수 및 1박 당 가격
            Room room = roomService.findRoomByPostsIdAndSize(postsId, s); //리스트로 list.get(0)에는 roomId, list.get(1)에는 count넣을 수 있는지 프론트와 상의하기
            count = dto.getMap().get(s); //예약할 마리 수
            totalCount += count;
            BookingInfo bookingInfo = BookingInfo.builder()
                    .roomName(room.getSize())
                    .count(count)
                    .build();
            bookingInfos.add(bookingInfo);

            Integer occupiedRoomCount = availableReservationHelper.occupiedRoomCount(reservations, s, checkInDate, checkOutDate);
            System.out.println("예약 가능한 마리 수 : " + (room.getRoomCount() - occupiedRoomCount));
            if (room.getRoomCount() - occupiedRoomCount < count) {
                throw new RuntimeException("예약 가능한 마리 수 : " + (room.getRoomCount() - occupiedRoomCount));
            }

            price += room.getPrice() * count; //1박 당 가격
        }
        System.out.println("count = " + totalCount);


        Long period = checkInDate.until(checkOutDate, ChronoUnit.DAYS); //checkOut - checkIn 날짜 수 계산
        Integer totalPrice = Math.toIntExact(price * period);

        ReservationCreateDto reservationCreateDto = ReservationCreateDto.builder()
                .bookingInfos(bookingInfos)
                .totalPrice(totalPrice)
                .totalDogCount(totalCount)
                .build();


        return reservationCreateDto; //총 가격
    }

    //예약 페이지 , 결제 페이지(예약 상세 페이지 ---> 예약 완료 페이지)
    public Reservation createReservation(ReservationCreateDto dto, Long userId, Long postsId, ReservationUserInfoDto userInfoDto) {

        Users users = usersService.findUsersById(userId); //validation check
        Posts posts = postsService.findById(postsId);

        LocalDate checkInDate = LocalDate.parse(dto.getCheckInDate(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate checkOutDate = LocalDate.parse(dto.getCheckOutDate(), DateTimeFormatter.ISO_LOCAL_DATE);

        LocalTime checkInTime = LocalTime.parse(dto.getCheckInTime(), DateTimeFormatter.ofPattern("a hh:mm").withLocale(Locale.KOREAN));
        LocalTime checkInOut = LocalTime.parse(dto.getCheckOutTime(), DateTimeFormatter.ofPattern("a hh:mm").withLocale(Locale.KOREAN));

        Long companyId = posts.getCompanyId();

        Reservation reservation = Reservation.builder()
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .checkInTime(checkInTime)
                .checkOutTime(checkInOut)
                .status("미정")
                .usersId(userId)
                .postsId(postsId)
                .companyId(companyId)
                .dogIdList(userInfoDto.getDogCardsId())
                .bookingInfos(dto.getBookingInfos())
                .userInfo(userInfoDto.getUserInfo())
                .dogCount(dto.getTotalDogCount())
                .totalPrice(dto.getTotalPrice())
                .build();

        reservation = reservationRepository.save(reservation);



        return reservation;
    }

    //예약 전체 조회(미래 예약 날짜)
    @Transactional(readOnly = true)
    public Page<Reservation> findReservationList(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "checkOutDate");

        return reservationRepository.findByUsersIdBooked(userId, LocalDate.now(), pageable);
    }


    //갔다 온 호텔 전체 조회
    @Transactional(readOnly = true)
    public Page<Reservation> findReservationAfterCheckOutList(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "checkOutDate");

        Page<Reservation> reservations = reservationRepository.findByUsersIdVisited(userId, LocalDate.now(), pageable);
        return reservations;

    }

    //예약 취
    public void deleteReservation(Long userId, Long reservedId) {

        Optional<Reservation> reservation = reservationRepository.findByUsersIdAndReservedId(userId, reservedId);
        Reservation findReservation = reservation.orElseThrow(NoSuchElementException::new);

        if (LocalDate.now().until(findReservation.getCheckInDate(), ChronoUnit.DAYS) < 1) {
            log.info("{}", findReservation.getCheckInDate().until(LocalDate.now(), ChronoUnit.DAYS));

            throw new RuntimeException("예약 취소는 하루 전에만 가능합니다");
        }

        reservationRepository.delete(findReservation);
    }

}
