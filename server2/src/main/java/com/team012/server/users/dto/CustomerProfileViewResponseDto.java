package com.team012.server.users.dto;

import com.team012.server.reservation.entity.ReservList;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.review.entity.Review;
import com.team012.server.users.entity.DogCard;
import com.team012.server.users.entity.Users;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileViewResponseDto {
    // 고객유저 정보
    private Users users;

    // 강아지 큐카드 정보
    private List<DogCard> dogCardList;

    // 예약 호캉스 내역
    private List<ReservList> reservationList;

    // 다녀온 호캉스 내역??? 상태가 다른 호텔값을 따로 뽑아서 하는지????


    // review 관리
    private List<Review> reviewList;
}
