package com.team012.server.posts.repository;

import lombok.Getter;

@Getter
public class RoomPriceDto {
    private Long postsId;
    private Integer price;

    //갯수

    public RoomPriceDto(Long postsId, Integer price) {
        this.postsId = postsId;
        this.price = price;
    }
}
