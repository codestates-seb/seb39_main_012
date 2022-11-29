package com.team012.server.posts.dto;

import com.team012.server.posts.img.entity.PostsImg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsMainPageDto {
    private Long id;
    private String title;
    private String address;
    private Double avgScore;  // 평균 점수 추가
    private Integer likesCount;

    private PostsImg postsImg;

    private Integer price; //minPrice

    public PostsMainPageDto(Long id, String title, String address, Double avgScore, Integer likesCount, PostsImg postsImg, Integer price) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.avgScore = avgScore;
        this.likesCount = likesCount;
        this.postsImg = postsImg;
        this.price = price;
    }
}
