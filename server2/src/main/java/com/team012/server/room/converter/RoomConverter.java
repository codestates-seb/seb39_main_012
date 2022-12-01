package com.team012.server.room.converter;

import com.team012.server.common.utils.converter.Converter;
import com.team012.server.room.dto.RoomDto;
import com.team012.server.room.entity.Room;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomConverter implements Converter<Room, RoomDto> {

    @Override
    public RoomDto toDTO(Room room) {
        return RoomDto.builder()
                .roomId(room.getId())
                .size(room.getRoomSize())
                .price(room.getPrice())
                .roomCount(room.getRoomCount())
                .build();
    }

    @Override
    public Room toEntity(RoomDto roomDto) {
        return null;
    }

    @Override
    public List<RoomDto> toListDTO(List<Room> list) {
        return list.stream().map(this::toDTO)
                .collect(Collectors.toList());
    }
}
