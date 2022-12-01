package com.team012.server.room.service;

import com.team012.server.common.exception.BusinessLogicException;
import com.team012.server.common.exception.ExceptionCode;
import com.team012.server.room.dto.RoomCreateDto;
import com.team012.server.room.entity.Room;
import com.team012.server.room.repository.RoomJDBCRepository;
import com.team012.server.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Transactional
@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomJDBCRepository roomJDBCRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public void saveList(List<RoomCreateDto> list, Long postsId) {
        List<Room> roomList = new ArrayList<>();
        for(RoomCreateDto room : list) {
            Room room1 = Room.builder()
                    .roomSize(room.getSize())
                    .price(room.getPrice())
                    .roomCount(room.getRoomCount())
                    .postsId(postsId)
                    .build();
            roomList.add(room1);
        }

        roomJDBCRepository.batchInsert(roomList);
    }

    public List<Room> updateRoomList(List<RoomCreateDto> roomDto, Long postsId) {
        List<Room> rooms = roomRepository.findAllByPostsId(postsId);
        if(rooms.size() != 3) throw new IllegalArgumentException("3개");
        for(int i = 0; i< roomDto.size(); i++) {
            Room room = rooms.get(i);
            room.setPrice(roomDto.get(i).getPrice());
            room.setRoomCount(roomDto.get(i).getRoomCount());
        }
        return roomRepository.saveAll(rooms);
    }

    @Transactional(readOnly = true)
    public Room findRoomByPostsIdAndSize(Long postsId, String size) {
        return roomRepository.findByPostsIdAndSize(postsId, size)
                .orElseThrow(() -> new RuntimeException("room not found"));
    }
    @Transactional(readOnly = true)
    public List<Room> findAllRoom(Long postsId) {
        List<Room> roomList = roomRepository.findAllByPostsId(postsId);
        return roomList;
    }

    public void deleteRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new BusinessLogicException(ExceptionCode.ROOM_NOT_EXIST));
        roomRepository.delete(room);
    }

    public void deleteAll(Long postsId) {
        List<Room> roomList = roomRepository.findAllByPostsId(postsId);
        roomRepository.deleteAll(roomList);
    }
    @Transactional(readOnly = true)
    public Integer findMinPrice(Long postId) {
        return roomRepository.findMinPrice(postId);
    }




}
