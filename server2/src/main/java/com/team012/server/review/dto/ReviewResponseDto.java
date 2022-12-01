package com.team012.server.review.dto;

import com.team012.server.review.entity.ReviewImg;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
public class ReviewResponseDto {

    private Long id;
    private String title;
    private String content;
    private Double score;
    private List<ReviewImg> reviewImgList;
    private String message;

    @Builder
    public ReviewResponseDto(Long id,
                             String title,
                             String content,
                             Double score,
                             List<ReviewImg> reviewImgList,
                             String message) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.score = score;
        this.reviewImgList = reviewImgList;
        this.message = message;
    }
}
