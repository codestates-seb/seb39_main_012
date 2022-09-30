package com.team012.server.posts.Tag.HashTag.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HashTagResponseDto {
    private Long hashTagId;
    private String tag;
}
