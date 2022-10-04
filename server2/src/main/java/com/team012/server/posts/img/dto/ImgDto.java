package com.team012.server.posts.img.dto;

import com.team012.server.posts.img.entity.PostsImg;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class ImgDto {
    private Long id;
    private String fileName;
    private String url;

}
