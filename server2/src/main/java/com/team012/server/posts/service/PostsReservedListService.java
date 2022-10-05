package com.team012.server.posts.service;

import com.team012.server.posts.dto.PostsReservationListDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.img.repository.PostsImgRepository;
import com.team012.server.posts.repository.PostsRepository;
import com.team012.server.reservation.entity.ReservationList;
import com.team012.server.reservation.service.CustomerReservationService;
import com.team012.server.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsReservedListService {
    private final PostsRepository postsRepository;
    private final RoomService roomService;

    private final CustomerReservationService customerReservationService;

    public List<PostsReservationListDto> findReservedHotels(List<ReservationList> reservationLists) {
        List<Long> postsIdList = reservationLists.stream().map(ReservationList::getPostsId).collect(Collectors.toList());

        List<PostsReservationListDto> bookedList = new ArrayList<>();
        for (Long postsId : postsIdList) {
            Posts findPosts = postsRepository.findById(postsId).orElse(null);
            String url = findPosts.getPostsImgList().get(0).getImgUrl();
            Integer roomPrice = roomService.findMinPrice(postsId);

            PostsReservationListDto postsReservationListDto
                    = PostsReservationListDto.builder()
                    .title(findPosts.getTitle())
                    .roomPrice(roomPrice)
                    .url(url)
                    .build();
            bookedList.add(postsReservationListDto);
        }
        return bookedList;
    }


}
