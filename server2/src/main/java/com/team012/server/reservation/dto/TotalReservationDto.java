package com.team012.server.reservation.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class TotalReservationDto {

        // 강아지 정보
        private String photoImgUrl;

        private String dogName;

        private String type;

        private String gender;

        private Integer age;

        private Double weight;

        private String snackMethod;

        private String bark;

        private String surgery;

        private String bowelTrained;

        private String etc;

        // 예약자 정보
        private String phone;

        private String name;


        // 예약 정보
        private String address;

        private String checkInDate;

        private String checkOutDate;

        private String checkInTime;

        private String checkOutTime;

        private Integer totalPrice;

}
