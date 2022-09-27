package com.team012.server.users.controller;

import com.team012.server.reservation.entity.ReservList;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.reservation.service.ReservationService;
import com.team012.server.review.entity.Review;
import com.team012.server.review.service.ReviewService;
import com.team012.server.users.dto.CustomerProfileViewResponseDto;
import com.team012.server.users.entity.DogCard;
import com.team012.server.users.entity.Users;
import com.team012.server.users.service.DogCardService;
import com.team012.server.users.service.UsersManageReviewService;
import com.team012.server.users.service.UsersService;
import com.team012.server.utils.config.userDetails.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // 회원 상세페이지
    @GetMapping("/profile")
    public ResponseEntity getCustomer(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUsers().getId();
        log.info("userId : {}", userId);
        Users findUser = usersService.findUsersById(userId);
        List<DogCard> dogCardList = dogCardService.getListDogCard(userId);
        List<Review> reviewList = usersReviewManageReviewService.getListReview(userId);
        List<ReservList> reservationList = reservationService.getReservation(userId, 0, 6).getContent();

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
