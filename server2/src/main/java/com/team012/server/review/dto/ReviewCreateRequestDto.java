package com.team012.server.review.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequestDto {
    private String content;
    private Integer score;
    private Long postsId;
}
