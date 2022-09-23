package com.team012.server.reservation.service;


import com.team012.server.company.room.entity.Room;
import com.team012.server.company.room.service.RoomService;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.service.PostsService;
import com.team012.server.reservation.dto.*;
import com.team012.server.reservation.entity.Reservation;
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

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerReservationService {
    //고객 기준

    private final ReservationRepository reservationRepository;
    private final UsersService usersService;
    private final RoomService roomService;
    private final PostsService postsService;
    private final DogCardService dogCardService;


    // 상세 페이지에서 예약 내역 적는 부분(posts 상세 페이지 ---> 예약 상세 페이지)
    public CreateReservationDto registerReservation(RegisterReservationDto dto, Long userId, Long postsId) {
        usersService.findUsersById(userId); //validation check

        Integer dogCount = 0;

        Set<String> set = dto.getMap().keySet();
        List<String> list = new ArrayList<>(set);
        for(String s : list) {
            Integer count = dto.getMap().get(s);
            dogCount += count;

        }
        Integer totalPrice = calculatePriceAndAvailableBooking(dto, postsId);

        return CreateReservationDto.builder()
                .checkIn(dto.getCheckIn())
                .checkOut(dto.getCheckOut())
                .totalPrice(totalPrice)
                .dogCount(dogCount)
                .postsId(postsId)
                .build();
    }

    //돈 계산 및 에약 가능 여부 판단.
    public Integer calculatePriceAndAvailableBooking(RegisterReservationDto dto, Long postsId) {

        LocalDate checkIn = LocalDate.parse(dto.getCheckIn(), DateTimeFormatter.ISO_DATE);
        LocalDate checkOut = LocalDate.parse(dto.getCheckOut(), DateTimeFormatter.ISO_DATE);

        int price = 0;
        Integer count = 0;
        Set<String> set = dto.getMap().keySet();
        for(String s : set) { //예약할 마리 수 및 1박 당 가격
            Room room = roomService.findRoomByPostsIdAndSize(postsId ,s);
            count += dto.getMap().get(s); //예약할 마리 수
            price += room.getPrice() * count; //1박 당 가격
        }
        System.out.println("count = "+ count);

        Long companyId = postsService.findById(postsId).getCompanyId();
        Integer bookedCount = reservationRepository.findByCheckInCheckOut(checkIn, checkOut, companyId); //예약 되어있는 총 강아지 수
        System.out.println("bookedCount = " + bookedCount);

        Integer roomCount = postsService.findById(postsId).getRoomCount();
        //posts로 totalCount 구하기

        System.out.println("roomCount : " + roomCount);
        System.out.println("bookedCount + count : " + (bookedCount + count));

        if(roomCount < (bookedCount + count)) throw new RuntimeException("예약 가능한 마리 수 : " + (roomCount - bookedCount));
        //수용가능 마리수보다 기존에 예약되어있던 마리수 + 예약하려는 마리수가 더 크면 예약 불가라고 예외 처리

        Long period = checkIn.until(checkOut, ChronoUnit.DAYS); //checkOut - checkIn 날짜 수 계산

        return Math.toIntExact(price * period); //총 가격
    }

    //예약 페이지 , 결제 페이지(예약 상세 페이지 ---> 예약 완료 페이지)
    public ResponseReservationDto createReservation(CreateReservationDto dto, Long userId, ReservationUserInfoDto reservationUserInfoDto) {
        Users users = usersService.findUsersById(userId); //validation check
        Posts posts = postsService.findById(dto.getPostsId());

        Long companyId = posts.getCompanyId();

        String strCheckIn = dto.getCheckIn();
        String strCheckOut = dto.getCheckOut();

        LocalDate checkIn = LocalDate.parse(strCheckIn,DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate checkOut = LocalDate.parse(strCheckOut,DateTimeFormatter.ISO_LOCAL_DATE);

        //당일 예약 불가 및 하루 전날 예약 불가
        if(checkIn.isBefore(LocalDate.now().plusDays(1)) || checkOut.isBefore(LocalDate.now().plusDays(1))) {
            throw new RuntimeException("당일예약 불가 및 오늘 이전 날짜 예약 불가");
        }

        //reservation 저장
        Reservation reservation = Reservation.builder()
                .postsId(dto.getPostsId())
                .checkIn(LocalDate.parse(dto.getCheckIn(),DateTimeFormatter.ISO_LOCAL_DATE))
                .checkOut(LocalDate.parse(dto.getCheckOut(),DateTimeFormatter.ISO_LOCAL_DATE))
                .companyId(companyId)
                .dogCount(dto.getDogCount())
                .dogIdList(reservationUserInfoDto.getDogCardsId())
                .userInfo(reservationUserInfoDto.getUserInfo())
                .totalPrice(dto.getTotalPrice())
                .status("미정") //미정
                .usersId(userId)
                .build();

        //개 카드 찾기
        List<Long> dogId = reservationUserInfoDto.getDogCardsId();
        List<DogCard> dogCards = new ArrayList<>();
        for(Long i : dogId) {
            DogCard dogCard = dogCardService.findById(i);
            dogCards.add(dogCard);
        }

        //예약 완료 화면에 띄울 response
        ResponseReservationDto responseReservationDto = ResponseReservationDto.builder()
                .address(posts.getAddress() + " "+ posts.getDetailAddress())
                .username(users.getUsername())
                .phone(reservationUserInfoDto.getUserInfo().getPhone())
                .checkIn(strCheckIn)
                .checkOut(strCheckOut)
                .dogcard(dogCards)
                .totalPrice(dto.getTotalPrice())
                .build();


        reservationRepository.save(reservation);

        return responseReservationDto;
    }

    //예약 전체 조회(미래 예약 날짜)
    public Page<Reservation> findReservationList(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "checkOut");

        Page<Reservation> reservations =  reservationRepository.findByUsersId(userId,LocalDate.now(), pageable);
        return reservations;
    }

    public Page<Reservation> getReservation(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "companyId");

        // companyId 기준으로 내림차순 정렬
        return reservationRepository.findAll(pageable);
    }

    //갔다 온 호텔 전체 조회
    public Page<Reservation> findReservationAfterCheckOutList(Long userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "checkOut");

        Page<Reservation> reservations =  reservationRepository.findByUsersIdAndWent(userId, LocalDate.now(), pageable);
        return reservations;

    }

    //예약 취소
    public void deleteReservation(Long userId, Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("reservation not found"));
        if(!Objects.equals(userId, reservation.getUsersId())) throw new RuntimeException("본인의 예약만 삭제 가능");

        if(reservation.getCheckIn().until(LocalDate.now(),ChronoUnit.DAYS) < 1) {
            throw new RuntimeException("예약 취소는 하루 전에만 가능합니다");
        }

        reservationRepository.delete(reservation);
    }


    public Integer findReservations(String checkIn, String checkOut, Long companyId) {
        LocalDate checkInDate = LocalDate.parse(checkIn,DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate checkOutDate = LocalDate.parse(checkOut,DateTimeFormatter.ISO_LOCAL_DATE);

        return reservationRepository.findByCheckInCheckOut(checkInDate, checkOutDate,companyId);
    }
}
