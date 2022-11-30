package com.team012.server.posts.dto;

import com.team012.server.posts.img.dto.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostsResponseListDto {
    private Long id;
    private String title;
    private String address;
    private Double avgScore;  // 평균 점수 추가
    private Integer likesCount;
    private ImageDto img;
    private Integer minPrice;


}
