package com.team012.server.company.room.converter;

import com.team012.server.company.room.dto.RoomResponseDto;
import com.team012.server.company.room.entity.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomConvert {
    public List<RoomResponseDto> roomDtoList(List<Room> roomList) {
        List<RoomResponseDto> roomResponseDtoList = new ArrayList<>();
        for (Room room : roomList) {
            RoomResponseDto roomResponseDtoDto = RoomResponseDto.builder()
                    .roomId(room.getId())
                    .size(room.getSize())
                    .price(room.getPrice())
                    .build();
            roomResponseDtoList.add(roomResponseDtoDto);
        }
        return roomResponseDtoList;
    }

    public RoomResponseDto toRoomResponseDto(Room room) {
        return RoomResponseDto.builder()
                .roomId(room.getId())
                .size(room.getSize())
                .price(room.getPrice())
                .build();
    }

}
