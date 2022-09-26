package com.team012.server.reservation.controller;

import com.team012.server.reservation.dto.*;
import com.team012.server.reservation.entity.ReservList;
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

        // 예약
        ReservationCreateDto reservation
                = customerReservationService.registerReservation(registerReservationDto, userId, postsId);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    //계산 및 예약 가능 여부 확인
    @PostMapping("/{postsId}/calculate")
    public ResponseEntity<Integer> calculatePrice(@PathVariable("postsId") Long postsId,
                                                               @RequestBody RegisterReservationDto dto) {
        Integer price = customerReservationService.calculatePriceAndAvailableBooking(dto, postsId).get(1);
        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    //예약 상세 페이지 ---> 예약 완료 페이지
    @PostMapping("/{postsId}/confirm")
    public ResponseEntity confirmReservation(@PathVariable("postsId") Long postsId,
                                             @RequestBody ReservationConfirmDto dto,
                                             @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getUsers().getId();

        ReservationCreateDto reservationCreateDto = dto.getReservationCreateDto();
        ReservationUserInfoDto reservationUserInfoDto = dto.getReservationUserInfoDto();

        ResponseReservationDto responseReservationDto
                = customerReservationService.createReservation(reservationCreateDto, userId,postsId, reservationUserInfoDto);
        return new ResponseEntity<>(responseReservationDto, HttpStatus.CREATED);
    }

    //가기 전 호텔리스트(체크아웃 최신날짜 순)
    @GetMapping("/before")
    public ResponseEntity findReservationsBeforeCheckIn(@RequestParam int page, int size
                                                        ,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUsers().getId();
        Page<ReservList> reservationList =  customerReservationService.findReservationList(userId,page -1, size);
        List<ReservList> reservations = reservationList.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(reservations, reservationList), HttpStatus.OK);
    }

    //갔다온 호텔 리스트 (체크아웃 최신날짜 순)
    @GetMapping("/after")
    public ResponseEntity findReservationAfterCheckOut(@RequestParam int page, int size
                                                        ,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUsers().getId();
        Page<ReservList> reservationList =  customerReservationService.findReservationAfterCheckOutList(userId, page -1, size);
        List<ReservList> reservations = reservationList.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(reservations, reservationList), HttpStatus.OK);
    }

    //reservListId 로 삭제
    @DeleteMapping("/{reservListId}")
    public ResponseEntity deleteReservation(@PathVariable("reservListId") Long reservListId,
                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUsers().getId();
        customerReservationService.deleteReservation(userId, reservListId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // 예약 수정 API 생성이 어딘지 물어보고 하기
    @PatchMapping("/{reservationId}")
    public ResponseEntity updateReservation(@PathVariable("reservationId") Long id) {
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
