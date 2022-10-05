package com.team012.server.posts.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostsReservationListDto {
    private String title;
    private Integer roomPrice;
    private String url;

    @Builder
    public PostsReservationListDto(String title, Integer roomPrice, String url) {
        this.title = title;
        this.roomPrice = roomPrice;
        this.url = url;
    }
}
