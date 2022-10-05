package com.team012.server.reservation.controller;

import com.team012.server.common.config.userDetails.PrincipalDetails;
import com.team012.server.common.response.MultiResponseDto;
import com.team012.server.common.response.SingleResponseDto;
import com.team012.server.reservation.dto.*;
import com.team012.server.reservation.entity.ReservationList;
import com.team012.server.reservation.service.CustomerReservationService;
import com.team012.server.reservation.service.ReservationConfirmService;
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

    private final ReservationConfirmService reservationConfirmService;

    //posts 상세 페이지 ---> 예약 상세 페이지로 이동
    @PostMapping("/{postsId}")
    public ResponseEntity beforeReservation(@PathVariable("postsId") Long postsId,
                                            @RequestBody RegisterReservationDto registerReservationDto,
                                            @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getUsers().getId();

        // 예약
        ReservationCreateDto reservation
                = customerReservationService.registerReservation(registerReservationDto, userId, postsId);

        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    //예약 상세 페이지 ---> 예약 완료 페이지
    @PostMapping("/{postsId}/confirm")
    public ResponseEntity confirmReservation(@PathVariable("postsId") Long postsId,
                                             @RequestBody ReservationConfirmDto dto,
                                             @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getUsers().getId();

        ReservationCreateDto reservationCreateDto = dto.getReservationCreateDto();
        ReservationUserInfoDto reservationUserInfoDto = dto.getReservationUserInfoDto();

        ReservationList reservationList = customerReservationService.createReservation(reservationCreateDto, userId, postsId, reservationUserInfoDto);
        return new ResponseEntity<>(reservationList, HttpStatus.CREATED);
    }

    //예약 확인 페이지
    @GetMapping("/{reservationId}/final")
    public ResponseEntity finalReservation(@PathVariable("reservationId") Long reservationId,
                                           @AuthenticationPrincipal PrincipalDetails principalDetails) {

        List<TotalReservationDto> totalReservationDtos =
                reservationConfirmService.confirmReservation(principalDetails, reservationId);

        return new ResponseEntity(new SingleResponseDto<>(totalReservationDtos), HttpStatus.OK);
    }

    //가기 전 호텔리스트(체크아웃 최신날짜 순)
    @GetMapping("/before")
    public ResponseEntity findReservationsBeforeCheckIn(@RequestParam int page, int size
            , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUsers().getId();
        Page<ReservationList> reservationList = customerReservationService.findReservationList(userId, page - 1, size);
        List<ReservationList> reservations = reservationList.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(reservations, reservationList), HttpStatus.OK);
    }

    //갔다온 호텔 리스트 (체크아웃 최신날짜 순)
    @GetMapping("/after")
    public ResponseEntity findReservationAfterCheckOut(@RequestParam int page, int size
            , @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUsers().getId();
        Page<ReservationList> reservationList = customerReservationService.findReservationAfterCheckOutList(userId, page - 1, size);
        List<ReservationList> reservations = reservationList.getContent();

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

}
