package com.team012.server.reservation.service;


import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.service.PostsService;
import com.team012.server.reservation.dto.RegisterReservationDto;
import com.team012.server.reservation.dto.ReservationCreateDto;
import com.team012.server.reservation.dto.ReservationUserInfoDto;
import com.team012.server.reservation.entity.ReservationList;
import com.team012.server.reservation.helper.AvailableReservationHelper;
import com.team012.server.reservation.repository.ReservationListRepository;
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
    private final ReservationListRepository reservationListRepository;
    private final UsersService usersService;
    private final RoomService roomService;
    private final PostsService postsService;
    private final AvailableReservationHelper availableReservationHelper;


    // 상세 페이지에서 예약 내역 적는 부분(posts 상세 페이지 ---> 예약 상세 페이지)
    @Transactional(readOnly = true)
    public ReservationCreateDto registerReservation(RegisterReservationDto dto, Long userId, Long postsId) {
        usersService.findUsersById(userId); //validation check
        Long companyId = postsService.findById(postsId).getCompanyId();

        validateDate(dto.getCheckInDate(), dto.getCheckOutDate());

        Integer totalDogCount = calculatePriceAndAvailableBooking(dto, postsId).get(0);
        Integer totalPrice = calculatePriceAndAvailableBooking(dto, postsId).get(1);

        LocalDate checkInDate = LocalDate.parse(dto.getCheckInDate(),DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate checkOutDate = LocalDate.parse(dto.getCheckOutDate(),DateTimeFormatter.ISO_LOCAL_DATE);
        LocalTime checkInTime = LocalTime.parse(dto.getCheckInTime(), DateTimeFormatter.ofPattern("a hh:mm").withLocale(Locale.KOREA));
        LocalTime checkOutTime = LocalTime.parse(dto.getCheckOutTime(), DateTimeFormatter.ofPattern("a hh:mm").withLocale(Locale.KOREA));

        String str = dto.getCheckInDate() + dto.getCheckOutDate();
        str = str.replaceAll("-","");

        Long period = checkInDate.until(checkOutDate, ChronoUnit.DAYS);


        return ReservationCreateDto.builder()
                .dto(dto)
                .totalDogCount(totalDogCount)
                .totalPrice(totalPrice)
                .build();

    }

    @Transactional(readOnly = true)
    private void validateDate(String strCheckIn, String strCheckOut) {
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
    public List<Integer> calculatePriceAndAvailableBooking(RegisterReservationDto dto, Long postsId) {

        LocalDate checkInDate = LocalDate.parse(dto.getCheckInDate(), DateTimeFormatter.ISO_DATE);
        LocalDate checkOutDate = LocalDate.parse(dto.getCheckOutDate(), DateTimeFormatter.ISO_DATE).minusDays(1);

        int price = 0;
        Integer totalCount = 0;
        Integer count = 0;
        Set<String> set = dto.getMap().keySet();
        for (String s : set) { //예약할 마리 수 및 1박 당 가격
            Room room = roomService.findRoomByPostsIdAndSize(postsId, s); //리스트로 list.get(0)에는 roomId, list.get(1)에는 count넣을 수 있는지 프론트와 상의하기

            count = dto.getMap().get(s); //예약할 마리 수
            totalCount += count;
            price += room.getPrice() * count; //1박 당 가격
        }
        System.out.println("count = " + totalCount);

        Posts posts = postsService.findById(postsId);
        Long companyId = posts.getCompanyId();

        List<ReservationList> reservationLists = reservationListRepository.findByCheckInCheckOut(companyId);
        Integer occupiedRoomCount= availableReservationHelper.occupiedRoomCount(reservationLists, checkInDate, checkOutDate);
        System.out.println("예약 가능한 마리 수 : " + (posts.getRoomCount() - occupiedRoomCount));
        if(posts.getRoomCount() - occupiedRoomCount < totalCount) throw new RuntimeException("예약 가능한 마리 수 : " + (posts.getRoomCount() - occupiedRoomCount));


        Long period = checkInDate.until(checkOutDate, ChronoUnit.DAYS); //checkOut - checkIn 날짜 수 계산
        Integer totalPrice = Math.toIntExact(price * period);


        return List.of(totalCount, totalPrice); //총 가격
    }

    //예약 페이지 , 결제 페이지(예약 상세 페이지 ---> 예약 완료 페이지)
    public ReservationList createReservation(ReservationCreateDto dto, Long userId, Long postsId, ReservationUserInfoDto userInfoDto) {

        Users users = usersService.findUsersById(userId); //validation check
        Posts posts = postsService.findById(postsId);

        LocalDate checkInDate = LocalDate.parse(dto.getDto().getCheckInDate(),DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate checkOutDate = LocalDate.parse(dto.getDto().getCheckOutDate(),DateTimeFormatter.ISO_LOCAL_DATE);

        LocalTime checkInTime = LocalTime.parse(dto.getDto().getCheckInTime(),DateTimeFormatter.ofPattern("a hh:mm").withLocale(Locale.KOREAN));
        LocalTime checkInOut = LocalTime.parse(dto.getDto().getCheckOutTime(),DateTimeFormatter.ofPattern("a hh:mm").withLocale(Locale.KOREAN));

        Long companyId = posts.getCompanyId();

        ReservationList reservationList = ReservationList.builder()
                .checkInDate(checkInDate)
                .checkOutDate(checkOutDate)
                .checkInTime(checkInTime)
                .checkOutTime(checkInOut)
                .status("미정")
                .usersId(userId)
                .postsId(postsId)
                .companyId(companyId)
                .dogIdList(userInfoDto.getDogCardsId())
                .userInfo(userInfoDto.getUserInfo())
                .dogCount(dto.getTotalDogCount())
                .totalPrice(dto.getTotalPrice())
                .build();

        reservationList = reservationListRepository.save(reservationList);

        return reservationList;
    }

    //예약 전체 조회(미래 예약 날짜)
    @Transactional(readOnly = true)
    public Page<ReservationList> findReservationList(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "checkOutDate");

        return reservationListRepository.findByUsersIdBooked(userId,LocalDate.now(), pageable);
    }


    //갔다 온 호텔 전체 조회
    @Transactional(readOnly = true)
    public Page<ReservationList> findReservationAfterCheckOutList(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "checkOutDate");

        Page<ReservationList> reservationLists =  reservationListRepository.findByUsersIdVisited(userId, LocalDate.now(), pageable);
        return reservationLists;

    }

    //예약 취
    public void deleteReservation(Long userId, Long reservedId) {

        Optional<ReservationList> reservationList= reservationListRepository.findByUsersIdAndReservedId(userId, reservedId);
        ReservationList findReservationList = reservationList.orElseThrow(NoSuchElementException::new);

        if(LocalDate.now().until(findReservationList.getCheckInDate(),ChronoUnit.DAYS) < 1) {
            log.info("{}", findReservationList.getCheckInDate().until(LocalDate.now(),ChronoUnit.DAYS));

            throw new RuntimeException("예약 취소는 하루 전에만 가능합니다");
        }

        reservationListRepository.delete(findReservationList);
    }

}
