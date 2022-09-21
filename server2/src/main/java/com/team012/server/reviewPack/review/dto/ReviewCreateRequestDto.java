package com.team012.server.reviewPack.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReviewCreateRequestDto {

    private String content;
    private Integer score;
    private Long postsId;

    @Builder
    public ReviewCreateRequestDto(String content, Integer score, Long postsId) {
        this.content = content;
        this.score = score;
        this.postsId = postsId;
    }
}
