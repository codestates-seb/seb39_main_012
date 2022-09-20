package com.team012.server.review.controller;

import com.team012.server.review.dto.ReviewDto;
import com.team012.server.review.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/review")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // 고객이 업체 리뷰작성 API (임시)
    @PostMapping("/write")
    public ResponseEntity postReview(@RequestBody ReviewDto.Post dto,
                                     @RequestPart(value = "file") List<MultipartFile> multipartFile) {
        reviewService.createReview(dto, multipartFile);
        return new ResponseEntity<>("작성완료..!", HttpStatus.CREATED);
    }

    // 고객이 업체 리뷰수정 API
    @PatchMapping("/update")
    public ResponseEntity updateReview(@RequestBody ReviewDto.Patch dto,
                                       @RequestPart(value = "file") List<MultipartFile> multipartFile) {
        return new ResponseEntity<>("수정완료..!", HttpStatus.OK);
    }

    // 고객이 업체 리뷰삭제 API
    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity deleteReview(@PathVariable("reviewId") Long reviewId) {
        return new ResponseEntity<>("삭제완료..!", HttpStatus.OK);
    }
}
