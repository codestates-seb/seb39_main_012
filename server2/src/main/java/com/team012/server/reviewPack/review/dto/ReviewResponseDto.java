package com.team012.server.reviewPack.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewResponseDto {

    private Long id;
    private String content;
    private Integer score;

    @Builder
    public ReviewResponseDto(Long id, String content, Integer score) {
        this.id = id;
        this.content = content;
        this.score = score;
    }
}
