package com.team012.server.posts.img.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ImageDto {
    private Long id;
    private String fileName;
    private String url;

}
