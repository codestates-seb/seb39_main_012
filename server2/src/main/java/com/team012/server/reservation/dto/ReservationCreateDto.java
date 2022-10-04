package com.team012.server.reservation.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateDto {
    RegisterReservationDto dto;
    Integer totalDogCount;
    Integer totalPrice;

}
