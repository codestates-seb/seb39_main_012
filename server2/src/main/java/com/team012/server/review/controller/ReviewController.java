package com.team012.server.review.controller;

import com.team012.server.common.config.userDetails.PrincipalDetails;
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
        PostsAvgScore postsAvgScore = postsAvgScoreService.savedPostsScore(dto.getScore(), dto.getPostsId());

                // 응답객체
                ReviewResponseDto response = ReviewResponseDto
                .builder()
                .id(savedReview.getId())
                .content(savedReview.getContent())
                .score(savedReview.getScore())
                .reviewImgList(savedReview.getReviewImgList())
                .message("리뷰 작성 완료....!")
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    // 고객이 업체 리뷰수정 API (고객 마이페이지에서 수정)
    @PatchMapping("/update/{reviewId}")
    public ResponseEntity updateReview(@PathVariable("reviewId") Long id,
                                       @RequestPart(value = "dto") ReviewPatchRequestDto dto,
                                       @RequestPart(value = "multipartFile") List<MultipartFile> multipartFile) {
        Review updateReview = reviewService.updateReview(id, dto, multipartFile);

        // 응답객체
        ReviewResponseDto response = ReviewResponseDto
                .builder()
                .id(updateReview.getId())
                .content(updateReview.getContent())
                .score(updateReview.getScore())
                .reviewImgList(updateReview.getReviewImgList())
                .message("리뷰 수정 완료...!")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 고객이 업체 리뷰삭제 API
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        ReviewResponseDto response = ReviewResponseDto
                .builder()
                .message("리뷰 삭제 완료...!")
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
