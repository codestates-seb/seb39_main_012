package com.team012.server.reservation.service;


import com.team012.server.company.room.entity.Room;
import com.team012.server.company.room.service.RoomService;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.service.PostsService;
import com.team012.server.reservation.dto.*;
import com.team012.server.reservation.entity.ReservList;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.reservation.repository.ReservListRepository;
import com.team012.server.reservation.repository.ReservationRepository;

import com.team012.server.users.entity.DogCard;
import com.team012.server.users.entity.Users;
import com.team012.server.users.service.DogCardService;
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
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class CustomerReservationService {
    //고객 기준
    /*
     *
     * 예약 건수 세는 쿼리문에 문제가 있어 일단 그부분은 주석처리했습니다.
     * */

    private final ReservationRepository reservationRepository;
    private final ReservListRepository reservListRepository;
    private final UsersService usersService;
    private final RoomService roomService;
    private final PostsService postsService;
    private final DogCardService dogCardService;


    // 상세 페이지에서 예약 내역 적는 부분(posts 상세 페이지 ---> 예약 상세 페이지)
    @Transactional(readOnly = true)
    public ReservationCreateDto registerReservation(RegisterReservationDto dto, Long userId, Long postsId) {
        usersService.findUsersById(userId); //validation check
        Long companyId = postsService.findById(postsId).getCompanyId();

        validateDate(dto.getCheckIn(), dto.getCheckOut());

        Integer totalDogCount = calculatePriceAndAvailableBooking(dto, postsId).get(0);
        Integer totalPrice = calculatePriceAndAvailableBooking(dto, postsId).get(1);


        LocalDate checkIn = LocalDate.parse(dto.getCheckIn(),DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate checkOut = LocalDate.parse(dto.getCheckOut(),DateTimeFormatter.ISO_LOCAL_DATE);
        //2022-11-102022-11-10
        String str = dto.getCheckIn() + dto.getCheckOut();
        str = str.replaceAll("-","");

        Long period = checkIn.until(checkOut, ChronoUnit.DAYS);

        List<Reservation> reservations = new ArrayList<>();


        for(int i = 0; i< period;i ++) {
            Reservation reservation = Reservation.builder()
                    .reservationDate(checkIn.plusDays(i))
                    .companyId(companyId)
                    .dogCount(totalDogCount)
                    .build();
            reservations.add(reservation);
        }

        ReservationCreateDto reservList = ReservationCreateDto.builder()
                .reservationList(reservations)
                .dto(dto)
                .checkIn(checkIn)
                .checkOut(checkOut)
                .totalDogCount(totalDogCount)
                .totalPrice(totalPrice)
                .reservationCode(str)
                .build();

        return reservList;
    }

    private void validateDate(String strCheckIn, String strCheckOut) {
        LocalDate checkIn = LocalDate.parse(strCheckIn, DateTimeFormatter.ISO_DATE);
        LocalDate checkOut = LocalDate.parse(strCheckOut, DateTimeFormatter.ISO_DATE);

        //당일 예약 불가 및 하루 전날 예약 불가
        if (checkIn.isBefore(LocalDate.now().plusDays(1)) || checkOut.isBefore(LocalDate.now().plusDays(1))) {
            throw new RuntimeException("당일예약 불가 및 오늘 이전 날짜 예약 불가");
        }
    }

    //돈 계산 및 에약 가능 여부 판단.
    @Transactional(readOnly = true)
    public List<Integer> calculatePriceAndAvailableBooking(RegisterReservationDto dto, Long postsId) {

        LocalDate checkIn = LocalDate.parse(dto.getCheckIn(), DateTimeFormatter.ISO_DATE);
        LocalDate checkOut = LocalDate.parse(dto.getCheckOut(), DateTimeFormatter.ISO_DATE).minusDays(1);

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
        System.out.println("count = " + count);

        Long companyId = postsService.findById(postsId).getCompanyId();
        List<Integer> bookedCount = reservationRepository.findByCheckInCheckOut(checkIn, checkOut, companyId); //예약 되어있는 총 강아지 수
        Integer maxCount = bookedCount.isEmpty() ? 0 : Collections.max(bookedCount);

        Integer roomCount = postsService.findById(postsId).getRoomCount();
        //posts로 totalCount 구하기

        System.out.println("roomCount : " + roomCount);
        System.out.println("bookedCount + count : " + (maxCount + count));

        if(roomCount < (maxCount + count)) throw new RuntimeException("예약 가능한 마리 수 : " + (roomCount - maxCount));
        //수용가능 마리수보다 기존에 예약되어있던 마리수 + 예약하려는 마리수가 더 크면 예약 불가라고 예외 처리

        Long period = checkIn.until(checkOut, ChronoUnit.DAYS); //checkOut - checkIn 날짜 수 계산
        Integer totalPrice = Math.toIntExact(price * period);


        return List.of(totalCount, totalPrice); //총 가격
    }

    //예약 페이지 , 결제 페이지(예약 상세 페이지 ---> 예약 완료 페이지)
    public ResponseReservationDto createReservation(ReservationCreateDto dto, Long userId, Long postsId,ReservationUserInfoDto userInfoDto) {

        Users users = usersService.findUsersById(userId); //validation check
        Posts posts = postsService.findById(postsId);

        Long companyId = posts.getCompanyId();

        ReservList reservList = ReservList.builder()
                .checkIn(dto.getCheckIn())
                .checkOut(dto.getCheckOut())
                .status("미정")
                .usersId(userId)
                .postsId(postsId)
                .companyId(companyId)
                .dogIdList(userInfoDto.getDogCardsId())
                .userInfo(userInfoDto.getUserInfo())
                .dogCount(dto.getTotalDogCount())
                .totalPrice(dto.getTotalPrice())
                .reservations(dto.getReservationList())
                .reservationCode(dto.getReservationCode())
                .build();

        ReservList reservList1 = reservListRepository.save(reservList);
        for(Reservation r : reservList1.getReservations()) {
            r.setReservList(reservList1);
        }

        reservationRepository.saveAll(dto.getReservationList());

        // 예약을 할 때 마다 등록된 방의 수에서 강아지 수에 따라 값을 빼준다.
        posts.setRoomCount(posts.getRoomCount() - reservation.getDogCount());

        // DB 데이터 반영
        postsService.save(posts);


        //개 카드 찾기
        List<Long> dogId = userInfoDto.getDogCardsId();
        List<DogCard> dogCards = new ArrayList<>();
        for (Long i : dogId) {
            DogCard dogCard = dogCardService.findById(i);
            dogCards.add(dogCard);
        }

        //예약 완료 화면에 띄울 response
        ResponseReservationDto responseReservationDto = ResponseReservationDto.builder()
                .address(posts.getAddress() + " "+ posts.getDetailAddress())
                .username(reservList1.getUserInfo().getName())
                .phone(reservList1.getUserInfo().getPhone())
                .checkIn(reservList1.getCheckIn().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .checkOut(reservList1.getCheckOut().format(DateTimeFormatter.ISO_LOCAL_DATE))
                .dogcard(dogCards)
                .totalPrice(reservList1.getTotalPrice())
                .build();

        return responseReservationDto;
    }

    //예약 전체 조회(미래 예약 날짜)
    @Transactional(readOnly = true)
    public Page<ReservList> findReservationList(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "checkOut");

        Page<ReservList> reservLists =  reservListRepository.findByUsersId(userId,LocalDate.now(), pageable);
        return reservLists;
    }

    @Transactional(readOnly = true)
    public Page<Reservation> getReservation(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "companyId");

        // companyId 기준으로 내림차순 정렬
        return reservationRepository.findAll(pageable);
    }

    //갔다 온 호텔 전체 조회
    @Transactional(readOnly = true)
    public Page<ReservList> findReservationAfterCheckOutList(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "checkOut");

        Page<ReservList> reservLists =  reservListRepository.findByUsersIdAndWent(userId, LocalDate.now(), pageable);
        return reservLists;

    }

    //예약 취
    public void deleteReservation(Long userId, Long reservedId) {

        Optional<ReservList> reservList= reservListRepository.findByUsersIdAndReservedId(userId, reservedId);
        ReservList findReservList = reservList.orElseThrow(NoSuchElementException::new);

        List<Reservation> reservation = findReservList.getReservations();

        if(LocalDate.now().until(findReservList.getCheckIn(),ChronoUnit.DAYS) < 1) {
            log.info("{}",findReservList.getCheckIn().until(LocalDate.now(),ChronoUnit.DAYS));

            throw new RuntimeException("예약 취소는 하루 전에만 가능합니다");
        }

        reservationRepository.deleteAll(reservation);
        reservListRepository.delete(findReservList);
    }
}
