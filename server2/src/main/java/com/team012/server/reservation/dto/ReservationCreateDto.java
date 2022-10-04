package com.team012.server.reservation.dto;

import com.team012.server.reservation.entity.Reservation;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateDto {
    List<Reservation> reservationList;
    RegisterReservationDto dto;
    LocalDate checkInDate;
    LocalDate checkOutDate;
    LocalTime checkInTime;
    LocalTime checkOutTime;
    Integer totalDogCount;
    Integer totalPrice;
}
