package com.team012.server.room.dto;

import lombok.Getter;

@Getter
public class RoomUpdateDto {
    private Long roomId;
    private String size;
    private Integer price;

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
}
