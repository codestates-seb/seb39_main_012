package com.team012.server.reservation.controller;

import com.team012.server.company.service.CompanyService;
import com.team012.server.reservation.dto.ReservationDto;
import com.team012.server.reservation.entity.ReservList;
import com.team012.server.reservation.service.ReservationService;
import com.team012.server.utils.config.userDetails.PrincipalDetails;
import com.team012.server.utils.response.MultiResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/company/reservation")
public class ReservationController {

    private final ReservationService reservationService;
    private final CompanyService companyService;

    public ReservationController(ReservationService reservationService, CompanyService companyService) {
        this.reservationService = reservationService;
        this.companyService = companyService;
    }

    // 예약현황 조회 API (회사기준)
    @GetMapping("/list/{companyId}")
    public ResponseEntity showReservation(@PathVariable("companyId") Long companyId,
                                          @RequestParam Integer page,
                                          @RequestParam Integer size,
                                          @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long findCompanyId = companyService.getCompany(principalDetails.getUsers().getId()).getId();
        if(companyId != findCompanyId) throw new RuntimeException("company가 일치하지 않음");

        Page<ReservList> reservationPage= reservationService.getReservation(companyId, page -1, size);
        List<ReservList> reservationList = reservationPage.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(reservationList, reservationPage), HttpStatus.OK);
    }

    // 예약확정 API (회사기준)
    @GetMapping("/confirm/{reservationId}")
    public ResponseEntity confirmReservation(@PathVariable("reservationId") Long reservationId) {
        reservationService.confirmReservation(reservationId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 예약취소 API (회사) --> 삭제
    @DeleteMapping("/cancel/{reservationId}")
    public ResponseEntity cancelReservation(@PathVariable("reservationId") Long id){

        reservationService.cancelReservation(id);
        ReservationDto.Response response =
                ReservationDto.Response
                        .builder()
                        .message("예약 삭제 성공")
                        .build();

        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
