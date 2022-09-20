package com.team012.server.reservation.controller;

import com.team012.server.reservation.dto.ReservationDto;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.reservation.service.ReservationService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    // 예약현황 조회 API (회사기준)
    @GetMapping("/list/{companyId}")
    public ResponseEntity showReservation(@PathVariable("companyId") Long companyId,
                                          @RequestParam Integer page,
                                          @RequestParam Integer size) {
        Page<Reservation> reservationList = reservationService.getReservation(companyId, page, size);
        ReservationDto.ReservationListResponse response =
                ReservationDto.ReservationListResponse
                        .builder()
                        .reservationList(reservationList)
                        .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 예약하기 API (고객기준)
    @PostMapping("/book")
    public ResponseEntity bookReservation(@RequestBody ReservationDto.Post dto) {

        Reservation reservation = reservationService.bookReservation(dto);
        ReservationDto.Response response =
                ReservationDto.Response
                        .builder()
                        .message("예약 성공")
                        .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 예약확정 API (회사기준)
    @PatchMapping("/confirm")
    public ResponseEntity confirmReservation(@RequestBody ReservationDto.Patch dto) {
        Reservation reservation = reservationService.confirmReservation(dto);
        ReservationDto.Response response =
                ReservationDto.Response
                        .builder()
                        .message("예약이 확정되었습니다.")
                        .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 예약수정 API (고객기준)
    @PatchMapping("/edit")
    public ResponseEntity editReservation(ReservationDto.Patch dto) {

        return null;
    }

    // 예약취소 API (고객 / 회사 전부가능) --> 삭제
    @DeleteMapping("/cancel/{reservationId}")
    public ResponseEntity cancelReservation(@PathVariable("reservationId") Long id) {
        reservationService.cancelReservation(id);
        ReservationDto.Response response =
                ReservationDto.Response
                        .builder()
                        .message("예약 삭제 성공")
                        .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
