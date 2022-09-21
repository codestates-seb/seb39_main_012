package com.team012.server.reviewPack.review.controller;

import com.team012.server.reviewPack.review.dto.ReviewCreateRequestDto;
import com.team012.server.reviewPack.review.dto.ReviewPatchRequestDto;
import com.team012.server.utils.config.userDetails.PrincipalDetails;
import com.team012.server.reviewPack.review.service.ReviewService;
import com.team012.server.usersPack.users.entity.Users;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 고객이 업체 리뷰작성 API (임시)
    @PostMapping("/review/write")
    public ResponseEntity postReview(@RequestPart(value = "dto") ReviewCreateRequestDto dto,
                                     @RequestPart(value = "multipartFile") List<MultipartFile> multipartFile,
                                     @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Users writeUser = principalDetails.getUsers();
        reviewService.createReview(dto, multipartFile, writeUser);
        return new ResponseEntity<>("작성완료..!", HttpStatus.CREATED);
    }


    // 고객이 업체 리뷰수정 API
    @PatchMapping("/review/update/{reviewId}")
    public ResponseEntity updateReview(@PathVariable("reviewId") Long id,
                                       @RequestPart(value = "dto") ReviewPatchRequestDto dto,
                                       @RequestPart(value = "multipartFile") List<MultipartFile> multipartFile) {
        reviewService.updateReview(id ,dto, multipartFile);
        return new ResponseEntity<>("수정완료..!", HttpStatus.OK);
    }

    // 고객이 업체 리뷰삭제 API
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>("삭제완료..!", HttpStatus.OK);
    }
}
