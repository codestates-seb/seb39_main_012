package com.team012.server.posts.Tag.HashTag.dto;

import lombok.Getter;

@Getter
public class HashTagUpdateDto {
    private Long hashTagId;
    private String tag;

    public void setHashTagId(Long hashTagId) {
        this.hashTagId = hashTagId;
    }
}
