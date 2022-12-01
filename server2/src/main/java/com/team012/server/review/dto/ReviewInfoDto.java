package com.team012.server.review.dto;

import com.team012.server.posts.dto.PostsReviewInfo;
import com.team012.server.review.entity.ReviewImg;
import lombok.*;

import java.util.List;

@Getter
public class ReviewInfoDto {
    private String createdAt;
    private String modifiedAt;
    private Long id;
    private String title;
    private String content;
    private Double score;
    private Long userId;
    private PostsReviewInfo companyInfo;
    private List<ReviewImg> reviewImg;

    @Builder
    public ReviewInfoDto(String createdAt, String modifiedAt, Long id, String title, String content, Double score, Long userId, PostsReviewInfo companyInfo, List<ReviewImg> reviewImg) {
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.id = id;
        this.title = title;
        this.content = content;
        this.score = score;
        this.userId = userId;
        this.companyInfo = companyInfo;
        this.reviewImg = reviewImg;
    }
}
