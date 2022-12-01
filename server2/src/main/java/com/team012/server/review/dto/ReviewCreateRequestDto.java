package com.team012.server.review.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Validated
public class ReviewCreateRequestDto {
    private String title;
    private String content;
    @Min(0) @Max(5)
    private Double score;
    private Long postsId;

    @Builder
    public ReviewCreateRequestDto(String title, String content, Double score, Long postsId) {
        this.title = title;
        this.content = content;
        this.score = score;
        this.postsId = postsId;
    }
}
