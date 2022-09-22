package com.team012.server.reviewPack.review.repository;

import com.team012.server.reviewPack.review.entity.ReviewImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewImgRepository extends JpaRepository<ReviewImg, Long> {
    List<ReviewImg> findByReview_Id(Long reviewId);
}
