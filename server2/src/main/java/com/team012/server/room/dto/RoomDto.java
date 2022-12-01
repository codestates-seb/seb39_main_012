package com.team012.server.room.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomDto {
    private Long roomId;
    private String size;
    private Integer price;
}
