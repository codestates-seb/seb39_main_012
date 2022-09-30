package com.team012.server.posts.Tag.ServiceTag.dto;

import lombok.Getter;

@Getter
public class ServiceTagUpdateDto {

    private Long serviceTagId;
    private String tag;
    public void setServiceTagId(Long serviceTagId) {
        this.serviceTagId = serviceTagId;
    }
}
