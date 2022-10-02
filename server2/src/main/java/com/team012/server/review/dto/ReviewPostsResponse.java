package com.team012.server.review.dto;

import com.team012.server.review.entity.ReviewImg;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPostsResponse {

    private Long id;
    private String createdAt;
    private String modifiedAt;
    private String title;
    private String content;
    private Double score;
    private String writer;
    private List<ReviewImg> reviewImgList;
}
