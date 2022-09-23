package com.team012.server.company.room.service;

import com.team012.server.company.entity.Company;
import com.team012.server.company.room.dto.RoomDto;
import com.team012.server.company.room.entity.Room;
import com.team012.server.company.room.repository.RoomRepository;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.repository.PostsRepository;
import com.team012.server.posts.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public List<Room> saveList(List<RoomDto.PostDto> list, Long postsId, Company company) {
        List<Room> roomList = new ArrayList<>();
        for(RoomDto.PostDto room : list) {
            Room room1 = Room.builder()
                    .size(room.getSize())
                    .price(room.getPrice())
                    .postsId(postsId)
                    .build();
            roomList.add(room1);
            room1.setCompany(company);
        }

        List<Room> list1 = roomRepository.saveAll(roomList);
        company.setRoom(roomList);
        return list1;
    }

    public Room update(Room room) {

        Optional<Room> c = roomRepository.findById(room.getId());
        Room findRoom = c.orElseThrow(() -> new RuntimeException("room not exist"));

        Optional.ofNullable(room.getSize()).ifPresent(findRoom::setSize);
        Optional.of(room.getPrice()).ifPresent(findRoom::setPrice);

        return roomRepository.save(findRoom);
    }

    public Room findRoom(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("room not exist"));
    }

    public Room findRoomByPostsIdAndSize(Long postsId, String size) {
        return roomRepository.findByPostsIdAndSize(postsId, size)
                .orElseThrow(() -> new RuntimeException("room not found"));
    }

    public List<Room> findAllRoom(Long postsId) {
        List<Room> roomList = roomRepository.findAllByPostsId(postsId);
        return roomList;
    }

    public void deleteRoom(Long roomId) {
        Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("room not exist"));
        roomRepository.delete(room);
    }

    public void deleteAll(Long postsId) {
        List<Room> roomList = roomRepository.findAllByPostsId(postsId);
        roomRepository.deleteAll(roomList);
    }

    public Integer findMinPrice(Long postId) {
        return roomRepository.findMinPrice(postId);
    }


}
