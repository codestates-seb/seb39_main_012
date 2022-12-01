package com.team012.server.review.dto;

import lombok.*;

@Getter
public class ReviewPatchRequestDto {

    private final String title;
    private final String content;
    private final Double score;

    @Builder
    public ReviewPatchRequestDto(String title, String content, Double score) {
        this.title = title;
        this.content = content;
        this.score = score;
    }
}
