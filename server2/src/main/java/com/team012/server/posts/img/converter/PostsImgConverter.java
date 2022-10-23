package com.team012.server.posts.img.converter;

import com.team012.server.common.utils.converter.Converter;
import com.team012.server.posts.img.dto.ImgDto;
import com.team012.server.posts.img.entity.PostsImg;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostsImgConverter implements Converter<PostsImg,ImgDto> {

    public ImgDto convertToImgDto(List<PostsImg> postsImgList) {
        PostsImg p = postsImgList.get(0);
        return toDTO(p);
    }

    @Override
    public ImgDto toDTO(PostsImg postsImg) {
        return ImgDto.builder()
                .id(postsImg.getId())
                .url(postsImg.getImgUrl())
                .fileName(postsImg.getFileName())
                .build();
    }

    @Override
    public PostsImg toEntity(ImgDto imgDto) {
        return null;
    }

    @Override
    public List<ImgDto> toListDTO(List<PostsImg> postsImgList) {
        return postsImgList.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
