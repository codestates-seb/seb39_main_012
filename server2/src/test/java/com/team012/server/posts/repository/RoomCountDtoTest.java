//package com.team012.server.posts.repository;
//
//import com.team012.server.posts.entity.Posts;
//import com.team012.server.posts.img.entity.PostsImg;
//import com.team012.server.posts.img.repository.PostsImgRepository;
//import com.team012.server.posts.repository.PostsRepository;
//import com.team012.server.posts.repository.RoomPriceDto;
//import com.team012.server.room.entity.Room;
//import com.team012.server.room.repository.RoomRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalTime;
//import java.util.List;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//public class RoomCountDtoTest {
//
//    @Autowired
//    private PostsRepository postsRepository;
//
//    @Autowired
//    private PostsImgRepository postsImgRepository;
//
//    @Autowired
//    private RoomRepository roomRepository;
//
//
//    @Test
//    void minRoomPriceTest() {
//        Posts posts = Posts.builder()
//                .title("title")
//                .content("content")
//                .latitude("111111")
//                .longitude("2222222")
//                .address("aaaaaaa")
//                .detailAddress("Aaaaaaaa")
//                .phone("010-1111-1111")
//                .roomCount(10)
//                .companyId(1L)
//                .checkInTime(LocalTime.now())
//                .checkOutTime(LocalTime.now().plusHours(1))
//                .build();
//
//        PostsImg postsImg = PostsImg.builder()
//                .fileName("file")
//                .imgUrl("https://google.jpg")
//                .build();
//        postsImgRepository.save(postsImg);
//
//        posts.setPostsImgList(List.of(postsImg));
//        Posts p = postsRepository.save(posts);
//
//        Room room = Room.builder()
//                .postsId(p.getId())
//                .size("small")
//                .price(50000)
//                .build();
//
//        Room room2 = Room.builder()
//                .postsId(p.getId())
//                .size("small")
//                .price(60000)
//                .build();
//        Room room3 = Room.builder()
//                .postsId(p.getId())
//                .size("small")
//                .price(70000)
//                .build();
//
//        List<Room> lists = List.of(room, room2, room3);
//
//        roomRepository.saveAll(lists);
//
//        Posts posts1 = Posts.builder()
//                .title("title2")
//                .content("content2")
//                .latitude("1111112")
//                .longitude("22222223")
//                .address("aaaaaaab")
//                .detailAddress("Aaaaaaaab")
//                .phone("010-1111-2222")
//                .roomCount(9)
//                .companyId(2L)
//                .checkInTime(LocalTime.now())
//                .checkOutTime(LocalTime.now().plusHours(1))
//                .build();
//        posts1.setAvgScore(5.0);
//
//        PostsImg postsImg2 = PostsImg.builder()
//                .fileName("file2")
//                .imgUrl("https://google2.jpg")
//                .build();
//        postsImgRepository.save(postsImg2);
//
//        posts1.setPostsImgList(List.of(postsImg2));
//        Posts p2 = postsRepository.save(posts1);
//
//        Room room12 = Room.builder()
//                .postsId(p2.getId())
//                .size("small")
//                .price(30000)
//                .build();
//
//        Room room22 = Room.builder()
//                .postsId(p2.getId())
//                .size("small")
//                .price(40000)
//                .build();
//        Room room32 = Room.builder()
//                .postsId(p2.getId())
//                .size("small")
//                .price(50000)
//                .build();
//
//        List<Room> lists2 = List.of(room12, room22, room32);
//
//        roomRepository.saveAll(lists2);
//
//        Pageable pageable = PageRequest.of(0, 10, Sort.Direction.DESC, "id");
//
//
//        Page<RoomPriceDto> list = postsRepository.findAllRoomMinPrice(pageable);
//        List<RoomPriceDto> list1 = list.getContent();
//
//        for (RoomPriceDto r : list1) {
//            System.out.println(r.getPrice());
//        }
//
//    }
//}
