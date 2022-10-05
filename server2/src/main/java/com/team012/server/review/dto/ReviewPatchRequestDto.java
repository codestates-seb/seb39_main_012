package com.team012.server.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewPatchRequestDto {

    private String title;
    private String content;
    private Double score;

    @Builder
    public ReviewPatchRequestDto(String title, String content, Double score) {
        this.title = title;
        this.content = content;
        this.score = score;
    }
}
