package com.team012.server.reservation.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservedRoomInfo {
    private String roomName;
    private Integer count;

    @Builder
    public ReservedRoomInfo(String roomName, Integer count) {
        this.roomName = roomName;
        this.count = count;
    }
}
