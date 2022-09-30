package com.team012.server.company.room.controller;

import com.team012.server.company.room.converter.RoomConvert;
import com.team012.server.company.room.dto.RoomResponseDto;
import com.team012.server.company.room.dto.RoomUpdateDto;
import com.team012.server.company.room.entity.Room;
import com.team012.server.company.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/company/room") //room 수정, 삭제를 위한 컨트롤러
public class RoomController {

    private final RoomService roomService;
    private final RoomConvert roomConvert;

    @PatchMapping("/{room-id}")
    public ResponseEntity<RoomResponseDto> update(@PathVariable("room-id") Long roomId,
                                 @RequestBody RoomUpdateDto request) {
        request.setRoomId(roomId);
        Room room = roomService.update(request);
        RoomResponseDto roomResponseDto = roomConvert.toRoomResponseDto(room);

        return new ResponseEntity<>(roomResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{room-id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("room-id") Long roomId) {

        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
