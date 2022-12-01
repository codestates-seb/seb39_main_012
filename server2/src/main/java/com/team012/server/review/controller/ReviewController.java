package com.team012.server.review.controller;

import com.team012.server.common.config.userDetails.PrincipalDetails;
import com.team012.server.common.exception.ExceptionCode;
import com.team012.server.posts.entity.PostsAvgScore;
import com.team012.server.posts.service.PostsAvgScoreService;
import com.team012.server.review.dto.ReviewCreateRequestDto;
import com.team012.server.review.dto.ReviewPatchRequestDto;
import com.team012.server.review.dto.ReviewResponseDto;
import com.team012.server.review.entity.Review;
import com.team012.server.review.service.ReviewService;
import com.team012.server.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.team012.server.common.exception.ExceptionCode.DELETE_OTHER_USERS_REVIEW_IS_FORBIDDEN;
import static com.team012.server.common.utils.constant.Constant.*;

@RestController
@RequestMapping("/v1/customer/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    private final PostsAvgScoreService postsAvgScoreService;

    // 고객이 업체 리뷰작성 API (임시)
    @PostMapping("/write")
    public ResponseEntity postReview(@RequestPart(value = "dto") ReviewCreateRequestDto dto,
                                     @RequestPart(value = "multipartFile") List<MultipartFile> multipartFile,
                                     @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Users writeUser = principalDetails.getUsers();
        Review savedReview = reviewService.createReview(dto, multipartFile, writeUser);

        // 리뷰가 작성될 때 마다 리뷰 별점들이 postsAvgScore에 따로 저장된다
        postsAvgScoreService.savedPostsScore(dto.getScore(), dto.getPostsId());

        // 응답객체
        ReviewResponseDto response = ReviewResponseDto
                .builder()
                .id(savedReview.getId())
                .title(savedReview.getTitle())
                .content(savedReview.getContent())
                .score(savedReview.getScore())
                .reviewImgList(savedReview.getReviewImgList())
                .message(REVIEW_WRITTEN.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    // 고객이 업체 리뷰수정 API (고객 마이페이지에서 수정)
    @PatchMapping("/update/{reviewId}")
    public ResponseEntity updateReview(@PathVariable("reviewId") Long reviewId,
                                       @RequestPart(value = "dto") ReviewPatchRequestDto dto,
                                       @RequestPart(value = "multipartFile") List<MultipartFile> multipartFile,
                                       @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Users users = principalDetails.getUsers();
        Review review = reviewService.findByReviewId(reviewId);

        if (review.getUserId() != users.getId()) {
            return new ResponseEntity<>(ExceptionCode.EDIT_OTHER_USERS_REVIEW_IS_FORBIDDEN.getMessage(), HttpStatus.FORBIDDEN);
        }

        Review updateReview = reviewService.updateReview(reviewId, dto, multipartFile);

        // 응답객체
        ReviewResponseDto response = ReviewResponseDto
                .builder()
                .id(updateReview.getId())
                .title(updateReview.getTitle())
                .content(updateReview.getContent())
                .score(updateReview.getScore())
                .reviewImgList(updateReview.getReviewImgList())
                .message(MODIFIED_REVIEW_SUCCESS.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 고객이 업체 리뷰삭제 API
    @DeleteMapping("/delete/{review-id}")
    public ResponseEntity deleteReview(@PathVariable("review-id") Long reviewId,
                                       @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Users users = principalDetails.getUsers();
        Review review = reviewService.findByReviewId(reviewId);

        if (review.getUserId() != users.getId()) {
            return new ResponseEntity<>(DELETE_OTHER_USERS_REVIEW_IS_FORBIDDEN.getMessage(), HttpStatus.FORBIDDEN);
        }

        reviewService.deleteReview(reviewId);
        ReviewResponseDto response = ReviewResponseDto
                .builder()
                .message(DELETE_REVIEW_SUCCESS.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
