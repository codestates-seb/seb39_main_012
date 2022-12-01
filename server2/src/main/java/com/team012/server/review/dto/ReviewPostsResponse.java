package com.team012.server.review.dto;

import com.team012.server.review.entity.ReviewImg;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Getter
public class ReviewPostsResponse {

    private Long id;
    private String createdAt;
    private String modifiedAt;
    private String title;
    private String content;
    private Double score;
    private String writer;
    private List<ReviewImg> reviewImgList;

    @Builder
    public ReviewPostsResponse(Long id, String createdAt, String modifiedAt, String title, String content, Double score, String writer, List<ReviewImg> reviewImgList) {
        this.id = id;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
        this.title = title;
        this.content = content;
        this.score = score;
        this.writer = writer;
        this.reviewImgList = reviewImgList;
    }
}
