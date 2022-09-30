package com.team012.server.posts.img.converter;

import com.team012.server.posts.img.dto.ImgDto;
import com.team012.server.posts.img.entity.PostsImg;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertToImgDto {

    public List<ImgDto> convertToImgDtos(List<PostsImg> postsImgList) {
        List<ImgDto> imgDtos = new ArrayList<>();
        for (PostsImg postsImg : postsImgList) {
            ImgDto imgDto = ImgDto.builder()
                    .fileName(postsImg.getFileName())
                    .url(postsImg.getImgUrl())
                    .build();
            imgDtos.add(imgDto);
        }
        return imgDtos;
    }

    public ImgDto convertToImgDto(List<PostsImg> postsImgList) {
        PostsImg p = postsImgList.get(0);
        ImgDto imgDto = ImgDto.builder()
                .url(p.getImgUrl())
                .fileName(p.getFileName())
                .build();
        return imgDto;
    }
}
