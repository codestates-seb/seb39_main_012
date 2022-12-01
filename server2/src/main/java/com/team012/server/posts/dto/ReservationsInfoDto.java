package com.team012.server.posts.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReservationsInfoDto {

    private final String title;
    private final Integer roomPrice;
    private final String url;

    @Builder
    public ReservationsInfoDto(String title, Integer roomPrice, String url) {
        this.title = title;
        this.roomPrice = roomPrice;
        this.url = url;
    }
}
