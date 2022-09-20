package com.team012.server.users.controller;

import com.team012.server.reservation.entity.Reservation;
import com.team012.server.reservation.service.ReservationService;
import com.team012.server.response.SingleResponseDto;
import com.team012.server.users.dto.UsersDto;
import com.team012.server.users.entity.Users;
import com.team012.server.users.mapper.UsersMapper;
import com.team012.server.users.service.UsersService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@Validated
public class UsersController {

    private final UsersService usersService;

    private final ReservationService reservationService;

    private final UsersMapper mapper;

    public UsersController(UsersService usersService, ReservationService reservationService, UsersMapper mapper) {
        this.usersService = usersService;
        this.reservationService = reservationService;
        this.mapper = mapper;
    }

    // 회사 회원가입
    @PostMapping("/join/company")
    public ResponseEntity postCompany(@Valid @RequestBody UsersDto.CompanyPost dto) {
        usersService.createCompany(dto);
        UsersDto.MessageResponse response = UsersDto.MessageResponse.builder()
                .message("회원가입 완료..!")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 고객 회원가입
    @PostMapping("/join/customer")
    public ResponseEntity postCustomer(@Valid @RequestBody UsersDto.CustomerPost dto) {
        usersService.createCustomer(dto);
        UsersDto.MessageResponse response = UsersDto.MessageResponse.builder()
                .message("회원가입 완료..!")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 이메일 중복확인
    @GetMapping("/check")
    public Boolean checkEmail(@RequestParam String email) {
        return usersService.getEmail(email);
    }


    // 업체 정보 페이지 조회 (업체 정보 + 예약 현황)
    @GetMapping("/profile/{company_name}")
    public ResponseEntity getCompany(@PathVariable("company_name") String companyName,
                                     @Positive @RequestParam int page,
                                     @Positive @RequestParam int size) {

        // 회사의 정보가 있는지 확인
        Users company = usersService.getCompany(companyName);

        // 정보가 없는 경우
        if (company == null) throw new NullPointerException();

        // 예약정보 확인
        Page<Reservation> reservationPage = reservationService.getReservation(company.getId(), page, size);

        List<UsersDto.ReservationList> list = mapper.pageToUsersDtoReservationList(reservationPage);
//        for (int i = 0; i < reservationPage.getSize(); i++) {
//            UsersDto.ReservationList reservationList =
//                    UsersDto.ReservationList
//                            .builder()
//                            .id(reservationPage.getContent().get(i).getId())
//                            .companyName(company.getCompanyName())
//                            .checkIn(reservationPage.getContent().get(i).getCheckIn().toString())
//                            .checkOut(reservationPage.getContent().get(i).getCheckOut().toString())
//                            .user(reservationPage.getContent().get(i).getUsers().getUsername())
//                            .build();
//        }

        // 정보가 있는 경우 --> 응답 객체 만들어서 반환
        UsersDto.InfoResponse response =
                UsersDto.InfoResponse.builder()
                        .username(company.getUsername())
                        .phone(company.getPhone())
                        .email(company.getEmail())
                        .companyName(company.getCompanyName())
                        .reservationList(list)
                        .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 고객 마이페이지 조회
    @GetMapping("/profile/{customer_id}")
    public ResponseEntity getCustomer(@PathVariable("customer_id") Long id,
                                      @Positive @RequestParam Integer page,
                                      @Positive @RequestParam Integer size) {


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
