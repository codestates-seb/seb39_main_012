package com.team012.server.posts.controller;

import com.team012.server.company.room.entity.Room;
import com.team012.server.company.room.service.RoomService;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.mapper.PostsMapper;
import com.team012.server.posts.service.PostsSearchService;
import com.team012.server.posts.service.PostsService;
import com.team012.server.review.entity.Review;
import com.team012.server.review.service.ReviewService;
import com.team012.server.utils.response.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/posts")
@RestController
public class PostsInquiryController {

    private final PostsSearchService postsSearchService;
    private final PostsService postsService;
    private final RoomService roomService;
    private final ReviewService reviewService;
    private final PostsMapper mapper;

    // 메인페이지 조회 (별점 순으로 기준해서 정렬)
    @GetMapping
    public ResponseEntity gets(@RequestParam int page,
                               @RequestParam int size) {
        // avgScore 기준으로 정렬된 페이징 처리
        Page<Posts> postsPage = postsService.mainPageAvgScoreView(page - 1, size);
        List<Posts> postsList = postsPage.getContent();


        return new ResponseEntity<>(new MultiResponseDto<>(mapper.postsToResponseDtos(postsList, roomService), postsPage), HttpStatus.OK);
    }

    @GetMapping("/address")
    public ResponseEntity searchByAddress(@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam String address) {
        Page<Posts> postsPage = postsSearchService.findPostsByAddress(address, page-1, size);
        List<Posts> postsList = postsPage.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.postsToResponseDtos(postsList,roomService),postsPage), HttpStatus.OK);
    }

    @GetMapping("/title")
    public ResponseEntity searchByTitle(@RequestParam int page,
                                        @RequestParam int size,
                                        @RequestParam String title) {
        Page<Posts> postsPage = postsSearchService.findPostsByTitle(title, page-1, size);
        List<Posts> postsList = postsPage.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.postsToResponseDtos(postsList,roomService),postsPage), HttpStatus.OK);
    }

    @GetMapping("/tag")
    public ResponseEntity searchByTag(@RequestParam int page,
                                      @RequestParam int size,
                                      @RequestParam String tag) {
        Page<Posts> postsPage = postsSearchService.findByTag(tag, page-1, size);
        List<Posts> postsList = postsPage.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.postsToResponseDtos(postsList,roomService),postsPage), HttpStatus.OK);
    }

    //로그인 없이도 조회 가능
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id,
                              @RequestParam Integer page,
                              @RequestParam Integer size) {
        Posts response = postsService.findById(id);

        // 작성된 리뷰 리스트 페이징처리 해서 넣어주기
        List<Review> reviewPage = reviewService.findByPage(page - 1, size).getContent();
        List<Room> roomList = roomService.findAllRoom(id);

        // 현재날짜를 기준으로 체크아웃이 현재날짜를 지나면 roomCount 값을 예약한 강아지수 만큼 DB에 올려준다.
//        postsReservationService.checkRoomCount(id);

        return new ResponseEntity<>(mapper.postsToPostsViewDto(response, roomList, reviewPage), HttpStatus.OK);
    }
}
