package com.team012.server.reservation.dto;

import com.team012.server.reservation.entity.ReservList;
import com.team012.server.reservation.entity.Reservation;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ReservationCreateDto {
    List<Reservation> reservationList;
    RegisterReservationDto dto;
    LocalDate checkIn;
    LocalDate checkOut;
    Integer totalDogCount;
    Integer totalPrice;
    String reservationCode;

    @Builder

    public ReservationCreateDto(List<Reservation> reservationList, RegisterReservationDto dto, LocalDate checkIn, LocalDate checkOut, Integer totalDogCount, Integer totalPrice, String reservationCode) {
        this.reservationList = reservationList;
        this.dto = dto;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalDogCount = totalDogCount;
        this.totalPrice = totalPrice;
        this.reservationCode = reservationCode;
    }
}
