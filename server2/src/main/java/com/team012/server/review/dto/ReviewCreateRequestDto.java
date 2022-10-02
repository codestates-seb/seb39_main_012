package com.team012.server.review.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@Builder
@Validated
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequestDto {
    private String title;
    private String content;

    @Min(0) @Max(5)
    private Double score;
    private Long postsId;
}
