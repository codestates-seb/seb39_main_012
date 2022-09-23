package com.team012.server.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewPatchRequestDto {
    private String content;
    private Integer score;

    @Builder
    public ReviewPatchRequestDto(String content, Integer score) {
        this.content = content;
        this.score = score;
    }
}
