package com.team012.server.users.mapper;

import com.team012.server.reservation.entity.Reservation;
import com.team012.server.response.SingleResponseDto;
import com.team012.server.users.dto.UsersDto;
import com.team012.server.users.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsersMapper {
    List<UsersDto.ReservationList> pageToUsersDtoReservationList(Page<Reservation> reservationPage);

    SingleResponseDto<Users> usersToSingleResponseDto(Users users);
}
