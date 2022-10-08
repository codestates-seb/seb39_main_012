package com.team012.server.users.controller;

import com.team012.server.common.aws.service.AwsS3Service;
import com.team012.server.review.dto.ReviewInfoDto;
import com.team012.server.common.config.userDetails.PrincipalDetails;
import com.team012.server.users.dto.CustomerProfileViewResponseDto;
import com.team012.server.users.entity.Users;
import com.team012.server.users.entity.UsersImg;
import com.team012.server.users.repository.UsersImgRepository;
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
    private final UsersManageReviewService usersReviewManageReviewService;

    private final UsersImgRepository usersImgRepository;

    // 고객 상세페이지
    @GetMapping("/profile")
    public ResponseEntity getCustomer(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUsers().getId();
        log.info("userId : {}", userId);
        Users findUser = usersService.findUsersById(userId);

        List<ReviewInfoDto> reviewInfoDtoList = usersReviewManageReviewService.getListReviewInfoList(userId);
        // 유저 이미지 찾기
        UsersImg usersImg = usersImgRepository.findByUsersId(userId);

        String userImgUrl = null;

        if (usersImg != null) userImgUrl = usersImg.getImgUrl();

        // 예약 전 / 후는
        CustomerProfileViewResponseDto response
                = CustomerProfileViewResponseDto
                .builder()
                .users(findUser)
                .reviewList(reviewInfoDtoList)
                .usersImg(userImgUrl)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
