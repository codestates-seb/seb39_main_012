package com.team012.server.users.service;

import com.team012.server.review.entity.Review;
import com.team012.server.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@RequiredArgsConstructor
@Service
public class UsersManageReviewService {

    private final ReviewService reviewService;

    // 작성한 리뷰 유저 아이디를 통한 조회
    @Transactional(readOnly = true)
    public List<Review> getListReview(Long id) {
        return reviewService.getListReview(id);
    }


}
