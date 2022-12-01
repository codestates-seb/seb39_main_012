package com.team012.server.reservation.dto;

import lombok.Getter;
import java.util.Map;

@Getter
public class RegisterReservationDto {

    private Map<String, Integer> map;
    private String checkInDate;
    private String checkOutDate;
    private String checkInTime;
    private String checkOutTime;
}
