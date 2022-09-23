package com.team012.server.reservation.dto;

import lombok.Getter;
import java.util.Map;

@Getter
public class RegisterReservationDto {

    private Map<String, Integer> map;
    private String checkIn;
    private String checkOut;
}
