package com.team012.server.reviewPack.review.repository;

import com.team012.server.reviewPack.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
