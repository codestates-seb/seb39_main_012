package com.team012.server.reservation.dto;

import com.team012.server.reservation.entity.Reservation;
import lombok.Getter;

import java.util.List;

@Getter
public class ReservationConfirmDto {
    private ReservationCreateDto reservationCreateDto;
    private ReservationUserInfoDto reservationUserInfoDto;
}
