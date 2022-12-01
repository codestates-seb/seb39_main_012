package com.team012.server.posts.service;

import com.team012.server.posts.dto.ReservationsInfoDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationsBeforeOrAfterCheckOutService {

    /* 오늘 기준으로 사용자가 미래에 갈 호텔 리스트와 갔다온 호텔 리스트를 조회해주는 서비스 */
    private final PostsService postsService;
    private final RoomService roomService;

    public List<ReservationsInfoDto> reservedHotels(List<Reservation> reservations) {
        List<Long> postsIds = reservations.stream().map(Reservation::getPostsId).collect(Collectors.toList());

        List<ReservationsInfoDto> reservationsInfoDto = new ArrayList<>();
        for (Long postsId : postsIds) {
            Posts findPosts = postsService.findById(postsId);
            String url = findPosts.getPostsImgList().get(0).getImgUrl();
            Integer roomPrice = roomService.findMinPrice(postsId);

            ReservationsInfoDto postsReservationListDto = generateBookedListDto(findPosts, url, roomPrice);
            reservationsInfoDto.add(postsReservationListDto);
        }
        return reservationsInfoDto;
    }
    private ReservationsInfoDto generateBookedListDto(Posts posts, String url, int roomPrice) {
        return ReservationsInfoDto.builder()
                .title(posts.getTitle())
                .roomPrice(roomPrice)
                .url(url)
                .build();
    }

    /* DT <-> entity 변환을 service혹은 controller에서 할지 고민을 많이 했으나
     controller에 넘길 시 표현계층에 너무 불필요한 정보를 넘겨주는 것 같아
     service단에 남기는 것으로 결론내렸습니다. */

}
