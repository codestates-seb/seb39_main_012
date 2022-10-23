package com.team012.server.room.controller;

import com.team012.server.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/company/room") //room 수정, 삭제를 위한 컨트롤러
public class RoomController {

    private final RoomService roomService;

//    @PatchMapping("/{posts-id}")
//    public ResponseEntity<RoomDto> update(@PathVariable("posts-id") Long postsId,
//                                          @RequestBody List<RoomUpdateDto> request) {
//
//        RoomDto roomDto = roomService.update(request);
//
//        return new ResponseEntity<>(roomDto, HttpStatus.OK);
//    }

    @DeleteMapping("/{room-id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable("room-id") Long roomId) {

        roomService.deleteRoom(roomId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
