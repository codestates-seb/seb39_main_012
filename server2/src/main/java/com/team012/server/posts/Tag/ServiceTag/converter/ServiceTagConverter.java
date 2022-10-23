package com.team012.server.posts.Tag.ServiceTag.converter;

import com.team012.server.common.utils.converter.Converter;
import com.team012.server.posts.Tag.ServiceTag.dto.ServiceResponseDto;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ServiceTagConverter implements Converter<PostsServiceTag, ServiceResponseDto> {

    @Override
    public ServiceResponseDto toDTO(PostsServiceTag postsServiceTag) {
        return ServiceResponseDto.builder()
                .serviceTagId(postsServiceTag.getServiceTag().getId())
                .tag(postsServiceTag.getServiceTag().getTag())
                .build();
    }

    @Override
    public PostsServiceTag toEntity(ServiceResponseDto serviceResponseDto) {
        return null;
    }

    @Override
    public List<ServiceResponseDto> toListDTO(List<PostsServiceTag> list) {
        return list.stream().map(this::toDTO)
                .collect(Collectors.toList());
    }
}
