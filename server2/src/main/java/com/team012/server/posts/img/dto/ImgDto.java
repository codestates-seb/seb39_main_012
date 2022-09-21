package com.team012.server.posts.img.dto;

import lombok.Builder;
import lombok.Getter;

public class ImgDto {

    @Getter
    @Builder
    public static class ResponseListDto {
        private String fileName;
        private String url;
    }
}
