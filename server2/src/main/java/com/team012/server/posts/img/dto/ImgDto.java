package com.team012.server.posts.img.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImgDto {
    private String fileName;
    private String url;

}
