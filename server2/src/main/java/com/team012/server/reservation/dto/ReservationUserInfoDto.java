package com.team012.server.reservation.dto;

import com.team012.server.reservation.entity.UserInfo;
import com.team012.server.usersPack.users.entity.DogCard;
import lombok.Getter;

import java.util.List;

@Getter
public class ReservationUserInfoDto {
    private List<Long> dogCardsId;
    private UserInfo userInfo;
}
