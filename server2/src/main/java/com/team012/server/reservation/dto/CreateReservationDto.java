package com.team012.server.reservation.dto;

import com.team012.server.company.room.entity.Room;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.usersPack.users.entity.DogCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import java.util.List;
import java.util.Map;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateReservationDto {

    private Integer dogCount;
    private String checkIn;
    private String checkOut;
    private Integer totalPrice;
    private Long postsId;

}
