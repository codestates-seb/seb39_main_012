package com.team012.server.posts.Tag.HashTag.converter;

import com.team012.server.common.converter.Converter;
import com.team012.server.posts.Tag.HashTag.dto.HashTagResponseDto;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HashTagConverter implements Converter<PostsHashTags, HashTagResponseDto> {
    @Override
    public HashTagResponseDto toDTO(PostsHashTags postsHashTags) {
        return HashTagResponseDto.builder()
                .hashTagId(postsHashTags.getHashTag().getId())
                .tag(postsHashTags.getHashTag().getTag())
                .build();
    }

    @Override
    public PostsHashTags toEntity(HashTagResponseDto hashTagResponseDto) {
        return null;
    }

    @Override
    public List<HashTagResponseDto> toListDTO(List<PostsHashTags> list) {
        return list.stream().map(this::toDTO)
                .collect(Collectors.toList());
    }
}
