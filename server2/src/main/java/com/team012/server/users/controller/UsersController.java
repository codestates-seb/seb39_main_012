package com.team012.server.users.controller;

import com.team012.server.reservation.entity.Reservation;
import com.team012.server.reservation.service.ReservationService;
import com.team012.server.review.entity.Review;
import com.team012.server.users.dto.CustomerProfileViewResponseDto;
import com.team012.server.users.dto.CustomerSignUpRequestDto;
import com.team012.server.users.dto.UsersMessageResponseDto;
import com.team012.server.users.dto.CompanySignUpRequestDto;
import com.team012.server.users.entity.DogCard;
import com.team012.server.users.entity.Users;
import com.team012.server.users.service.DogCardService;
import com.team012.server.users.service.UsersManageCompanyService;
import com.team012.server.users.service.UsersManageReviewService;
import com.team012.server.users.service.UsersService;
import com.team012.server.utils.config.userDetails.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/users")
@Validated
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UsersService usersService;
    private final DogCardService dogCardService;
    private final ReservationService reservationService;
    private final UsersManageReviewService usersReviewManageReviewService;
    private final UsersManageCompanyService usersManageCompanyService;

    // 회사 회원가입
    @PostMapping("/join/company")
    public ResponseEntity postCompany(@Valid @RequestBody CompanySignUpRequestDto dto) {
        usersManageCompanyService.createCompany(dto);
        UsersMessageResponseDto response = UsersMessageResponseDto
                .builder()
                .message("회원가입 완료..!")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 고객 회원가입
    @PostMapping("/join/customer")
    public ResponseEntity postCustomer(@Valid @RequestBody CustomerSignUpRequestDto dto) {
        usersService.createCustomer(dto);
        UsersMessageResponseDto response = UsersMessageResponseDto
                .builder()
                .message("회원가입 완료..!")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 이메일 중복확인
    @GetMapping("/check")
    public Boolean checkEmail(@RequestParam String email) {
        return usersService.getEmail(email);
    }

    // 회원 상세페이지
    @GetMapping("/customer/profile")
    public ResponseEntity getCustomer(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUsers().getId();
        log.info("userId : {}", userId);
        Users findUser = usersService.findUsersById(userId);
        List<DogCard> dogCardList = dogCardService.getListDogCard(userId);
        List<Review> reviewList = usersReviewManageReviewService.getListReview(userId);
        List<Reservation> reservationList = reservationService.getReservation(userId, 0, 6).getContent();

        CustomerProfileViewResponseDto response
                = CustomerProfileViewResponseDto
                .builder()
                .users(findUser)
                .dogCardList(dogCardList)
                .reviewList(reviewList)
                .reservationList(reservationList)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
