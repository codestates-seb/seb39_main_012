package com.team012.server.users.controller;

import com.team012.server.reservation.entity.ReservationList;
import com.team012.server.reservation.service.ReservationService;
import com.team012.server.review.entity.Review;
import com.team012.server.users.dto.CustomerUpdateRequestDto;
import com.team012.server.users.dto.UsersMessageResponseDto;
import com.team012.server.users.entity.DogCard;
import com.team012.server.common.config.userDetails.PrincipalDetails;
import com.team012.server.users.dto.CustomerProfileViewResponseDto;
import com.team012.server.users.entity.Users;
import com.team012.server.users.service.DogCardService;
import com.team012.server.users.service.UsersManageReviewService;
import com.team012.server.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final UsersService usersService;
    private final DogCardService dogCardService;
    private final ReservationService reservationService;
    private final UsersManageReviewService usersReviewManageReviewService;

    // 고객 상세페이지
    @GetMapping("/profile")
    public ResponseEntity getCustomer(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUsers().getId();
        log.info("userId : {}", userId);
        Users findUser = usersService.findUsersById(userId);
        List<DogCard> dogCardList = dogCardService.getListDogCard(userId);
        List<Review> reviewList = usersReviewManageReviewService.getListReview(userId);
        List<ReservationList> reservationList = reservationService.getReservation(userId, 0, 6).getContent();

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

    // 고객 정보 수정 API
    @PatchMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                         CustomerUpdateRequestDto dto) {
        Long userId = principalDetails.getUsers().getId();
        usersService.updateCustomer(userId, dto);
        UsersMessageResponseDto response =
                UsersMessageResponseDto
                        .builder()
                        .message("수정 완료..!")
                        .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
