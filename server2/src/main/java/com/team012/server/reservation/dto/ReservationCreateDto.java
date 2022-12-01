package com.team012.server.reservation.dto;

import com.team012.server.reservation.entity.ReservedRoomInfo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationCreateDto {
    private String checkInDate;
    private String checkOutDate;
    private String checkInTime;
    private String checkOutTime;
    private List<ReservedRoomInfo> reservedRoomInfos;
    private Integer totalPrice;
    private String companyName;

}
