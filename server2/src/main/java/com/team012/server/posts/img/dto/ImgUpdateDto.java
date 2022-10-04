package com.team012.server.posts.img.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ImgUpdateDto {

    private Long PostsImgId;

    private MultipartFile multipartFile;
}
