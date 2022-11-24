package com.team012.server.posts.service;

import com.team012.server.posts.dto.PostsReservationListDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.repository.PostsRepository;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.reservation.service.CustomerReservationService;
import com.team012.server.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsReservedListService {
    private final PostsRepository postsRepository;
    private final RoomService roomService;

    public List<PostsReservationListDto.BookedList> findReservedHotels(List<Reservation> reservations) {
        List<Long> postsIdList = reservations.stream().map(Reservation::getPostsId).collect(Collectors.toList());

        List<PostsReservationListDto.BookedList> bookedList = new ArrayList<>();
        for (Long postsId : postsIdList) {
            Posts findPosts = postsRepository.findById(postsId).orElse(null);
            String url = findPosts.getPostsImgList().get(0).getImgUrl();
            Integer roomPrice = roomService.findMinPrice(postsId);

            PostsReservationListDto.BookedList postsReservationListDto
                    = PostsReservationListDto.BookedList.builder()
                    .title(findPosts.getTitle())
                    .roomPrice(roomPrice)
                    .url(url)
                    .build();
            bookedList.add(postsReservationListDto);
        }
        return bookedList;
    }

    public List<PostsReservationListDto.BookedListAfterCheckOut> findReservedHotelsAfterCheckOut(List<Reservation> reservations) {
        List<Long> postsIdList = reservations.stream().map(Reservation::getPostsId).collect(Collectors.toList());

        List<PostsReservationListDto.BookedListAfterCheckOut> bookedList = new ArrayList<>();
        for (Long postsId : postsIdList) {
            Posts findPosts = postsRepository.findById(postsId).orElse(null);
            String url = findPosts.getPostsImgList().get(0).getImgUrl();
            Integer roomPrice = roomService.findMinPrice(postsId);

            PostsReservationListDto.BookedListAfterCheckOut postsReservationListDto
                    = PostsReservationListDto.BookedListAfterCheckOut.builder()
                    .postsId(findPosts.getId())
                    .title(findPosts.getTitle())
                    .roomPrice(roomPrice)
                    .url(url)
                    .build();
            bookedList.add(postsReservationListDto);
        }
        return bookedList;
    }




}
