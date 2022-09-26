package com.team012.server.reservation.dto;

import com.team012.server.reservation.entity.Reservation;
import lombok.Getter;

@Getter
public class ReservationConfirmDto {
    private Reservation reservation;
    private ReservationUserInfoDto reservationUserInfoDto;
}
