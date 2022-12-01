package com.team012.server.review.dto;

import com.team012.server.posts.dto.PostsReviewInfo;
import com.team012.server.review.entity.ReviewImg;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
}
