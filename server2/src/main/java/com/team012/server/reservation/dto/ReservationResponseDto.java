package com.team012.server.reservation.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class ReservationResponseDto {

    private final String message;

    @Builder
    public ReservationResponseDto(String message) {
        this.message = message;
    }
}
