package com.team012.server.review.dto;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class ReviewPatchRequestDto {

    private final String title;
    private final String content;
    private final Double score;

}
