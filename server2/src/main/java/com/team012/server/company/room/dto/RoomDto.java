package com.team012.server.company.room.dto;

import lombok.Builder;
import lombok.Getter;

public class RoomDto {

    @Getter
    public static class PostDto {
        private String size;
        private Integer count;
        private Integer price;
//        private MultipartFile multipartFile;
    }

    @Getter
    public static class PatchDto {
        private String size;
        private Integer count;
        private Integer price;

    }

    @Getter
    @Builder
    public static class ResponseDto {
        private String size;
        private Integer count;
        private Integer price;

    }
}
