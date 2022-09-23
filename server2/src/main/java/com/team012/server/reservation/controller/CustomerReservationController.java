package com.team012.server.reservation.controller;

import com.team012.server.reservation.dto.*;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.reservation.service.CustomerReservationService;
import com.team012.server.utils.config.userDetails.PrincipalDetails;
import com.team012.server.utils.response.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer/reservation")
@RequiredArgsConstructor
public class CustomerReservationController {

    private final CustomerReservationService customerReservationService;

    //posts 상세 페이지 ---> 예약 상세 페이지로 이동
    @PostMapping("/{postsId}")
    public ResponseEntity beforeReservation(@PathVariable("postsId") Long postsId
            , @RequestBody RegisterReservationDto registerReservationDto,
                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getUsers().getId();
        CreateReservationDto createReservationDto
                = customerReservationService.registerReservation(registerReservationDto, userId, postsId);

        return new ResponseEntity<>(createReservationDto, HttpStatus.OK);
    }

    //계산 및 예약 가능 여부 확인
    @PostMapping("/{postsId}/calculate")
    public ResponseEntity<Integer> calculatePrice(@PathVariable("postsId") Long postsId,
                                                               @RequestBody RegisterReservationDto dto) {
        Integer price = customerReservationService.calculatePriceAndAvailableBooking(dto, postsId);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    //예약 상세 페이지 ---> 예약 완료 페이지
    @PostMapping("/{postsId}/confirm")
    public ResponseEntity confirmReservation(@PathVariable("postsId") Long postsId,
                                             @RequestBody ReservationConfirmDto dto,
                                             @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getUsers().getId();

        CreateReservationDto createReservationDto = dto.getCreateReservationDto();
        ReservationUserInfoDto reservationUserInfoDto = dto.getReservationUserInfoDto();

        ResponseReservationDto responseReservationDto
                = customerReservationService.createReservation(createReservationDto, userId, reservationUserInfoDto);
        return new ResponseEntity<>(responseReservationDto, HttpStatus.CREATED);
    }

    //가기 전 호텔리스트(체크아웃 최신날짜 순)
    @GetMapping("/before")
    public ResponseEntity findReservationsBeforeCheckIn(@RequestParam int page, int size
                                                        ,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUsers().getId();
        Page<Reservation> reservationList =  customerReservationService.findReservationList(userId,page -1, size);
        List<Reservation> reservations = reservationList.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(reservations, reservationList), HttpStatus.OK);
    }

    //갔다온 호텔 리스트 (체크아웃 최신날짜 순)
    @GetMapping("/after")
    public ResponseEntity findReservationAfterCheckOut(@RequestParam int page, int size
                                                        ,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUsers().getId();
        Page<Reservation> reservationList =  customerReservationService.findReservationAfterCheckOutList(userId, page -1, size);
        List<Reservation> reservations = reservationList.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(reservations, reservationList), HttpStatus.OK);
    }

    //reservationId로 삭제
    @DeleteMapping("/{reservationId}")
    public ResponseEntity deleteReservation(@PathVariable("reservationId") Long reservationId,
                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUsers().getId();
        customerReservationService.deleteReservation(userId, reservationId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/test")
    public ResponseEntity<Integer> test(@RequestParam String checkIn,
                               @RequestParam String checkOut,
                               @RequestParam Long companyId) {
        Integer find = customerReservationService.findReservations(checkIn, checkOut, companyId);
        return new ResponseEntity<>(find, HttpStatus.OK);
    }

}
