package com.team012.server.reservation.dto;

import com.team012.server.company.room.entity.Room;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class RegisterReservationDto {

    private Map<String, Integer> map;
    private String checkIn;
    private String checkOut;
}
