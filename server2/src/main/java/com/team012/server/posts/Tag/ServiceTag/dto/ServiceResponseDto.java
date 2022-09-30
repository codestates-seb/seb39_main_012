package com.team012.server.posts.Tag.ServiceTag.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceResponseDto {
    private Long serviceTagId;
    private String tag;

}
