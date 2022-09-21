package com.team012.server.company.room.service;

import com.team012.server.company.room.dto.RoomDto;
import com.team012.server.company.room.entity.Room;
import com.team012.server.company.room.repository.RoomRepository;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final PostsRepository postsRepository;

    public Room save(RoomDto.PostDto room, Long postsId) {

        Room room1 = Room.builder()
                .size(room.getSize())
                .count(room.getCount())
                .price(room.getPrice())
                .build();
        return roomRepository.save(room1);
    }

    public List<Room> saveList(List<RoomDto.PostDto> list, Long postsId) {
        List<Room> roomList = new ArrayList<>();
        for(RoomDto.PostDto room : list) {
//            MultipartFile file = room.getMultipartFile();
//            String url = awsS3Service.uploadFile(file);
//            String fileName = awsS3Service.originalFileName(file);

            Room room1 = Room.builder()
                    .size(room.getSize())
                    .count(room.getCount())
                    .price(room.getPrice())
//                    .roomImg(fileName)
//                    .roomImgUrl(url)
                    .postsId(postsId)
                    .build();
            roomList.add(room1);
        }

        return roomRepository.saveAll(roomList);
    }

    public Room update(Room room) {

        Optional<Room> c = roomRepository.findById(room.getId());
        Room findRoom = c.orElseThrow(() -> new RuntimeException("room not exist"));

        Optional.ofNullable(room.getSize()).ifPresent(findRoom::setSize);
        Optional.of(room.getCount()).ifPresent(findRoom::setCount);
        Optional.of(room.getPrice()).ifPresent(findRoom::setPrice);

        return roomRepository.save(findRoom);
    }

    public Room findRoom(Long roomId) {
        return roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("room not exist"));
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


    private Posts verifiedPosts(Long companyPostsId) {
        return postsRepository.findById(companyPostsId)
                .orElseThrow(() -> new RuntimeException("post not exist"));
    }

}
