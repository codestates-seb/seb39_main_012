package com.team012.server.company.room.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomResponseDto {
    private Long roomId;
    private String size;
    private Integer price;
}
