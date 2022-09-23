package com.team012.server.reservation.dto;

import com.team012.server.usersPack.users.entity.DogCard;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@Builder
public class ResponseReservationDto {

    private List<DogCard> dogcard;
    private String username;
    private String phone;
    private String address;
    private String checkIn;
    private String checkOut;
    private Integer totalPrice;

}
