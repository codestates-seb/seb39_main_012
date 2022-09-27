//package com.team012.server.posts.service;
//
//import com.team012.server.posts.entity.Posts;
//import com.team012.server.reservation.entity.Reservation;
//import com.team012.server.reservation.service.ReservationService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//public class PostsReservationService {
//
//    private final PostsService postsService;
//    private final ReservationService reservationService;
//
//    public void checkRoomCount(Long postsId) {
//        // 1. 현재 날짜 뽑아서 문자열로 만들어 주기
//        LocalDate now = LocalDate.now();
//
//        // 2. 게시글 아이디를 통해서 예약정보를 뽑아 온다 / 게시글도 가져온다.
//        List<Reservation> reservationList = reservationService.getReservationList(postsId);
//        Posts findPosts = postsService.findById(postsId);
//
//        // 3. 반복문을 돌려서 체크아웃 날짜 뽑아오기
//        for (Reservation reservation : reservationList) {
//            LocalDate checkOut = reservation.getCheckOut();
//            log.info("checkOut : {}", checkOut);
//
//            // 체크아웃 날짜보다 현재의 날짜가 앞서면 true --> posts room ++1
//            if (checkOut.isBefore(now)) {
//                findPosts.setRoomCount(findPosts.getRoomCount() + 1);
//                postsService.save(findPosts);
//            }
//        }
//    }
//
//}
