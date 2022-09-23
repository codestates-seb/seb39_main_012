package com.team012.server.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationDto {

    private Integer dogCount;
    private String checkIn;
    private String checkOut;
    private Integer totalPrice;
    private Long postsId;

}
