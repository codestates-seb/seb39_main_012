package com.team012.server.reservation.dto;

import lombok.Getter;
import java.util.Map;

@Getter
public class RegisterReservationDto {

    private Map<String, Integer> map; //리스트로 list.get(0)에는 roomId, list.get(1)에는 count넣을 수 있는지 프론트와 상의하기
    private String checkInDate;
    private String checkOutDate;
    private String checkInTime;
    private String checkOutTime;
}
