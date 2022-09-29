package com.team012.server.reservation.dto;

import com.team012.server.reservation.entity.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateDto {
    List<Reservation> reservationList;
    RegisterReservationDto dto;
    LocalDate checkIn;
    LocalDate checkOut;
    Integer totalDogCount;
    Integer totalPrice;
    String reservationCode;
}
