package com.team012.server.reservation.dto;

import com.team012.server.reservation.entity.BookingInfo;
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
    private List<BookingInfo> bookingInfos;
    private Integer totalDogCount;
    private Integer totalPrice;
    private String companyName;

}
