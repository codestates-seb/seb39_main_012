package com.team012.server.posts.controller;

import com.team012.server.room.entity.Room;
import com.team012.server.room.service.RoomService;
import com.team012.server.posts.service.PostsCombineService;
import com.team012.server.posts.dto.PostsResponseDto;
import com.team012.server.posts.dto.PostsResponseListDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.service.PostListService;
import com.team012.server.posts.service.PostsSearchService;
import com.team012.server.posts.service.PostsService;
import com.team012.server.review.entity.Review;
import com.team012.server.review.service.ReviewService;
import com.team012.server.common.response.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/posts")
@RestController
public class PostsPageController {

    private final PostsSearchService postsSearchService;
    private final PostsService postsService;
    private final RoomService roomService;
    private final ReviewService reviewService;
    private final PostListService postListService;
    private final PostsCombineService postsCombineService;

    // 메인페이지 조회 (별점 순으로 기준해서 정렬)
    @GetMapping
    public ResponseEntity gets(@RequestParam int page,
                               @RequestParam int size) {
        // avgScore 기준으로 정렬된 페이징 처리
        Page<Posts> postsPage = postsService.mainPageAvgScoreView(page - 1, size);
        List<Posts> postsList = postsPage.getContent();

        List<PostsResponseListDto> postsResponseListDtos = postListService.postsResponseListDtos(postsList);

        return new ResponseEntity<>(new MultiResponseDto<>(postsResponseListDtos, postsPage), HttpStatus.OK);
    }

    @GetMapping("/search-address")
    public ResponseEntity searchByAddress(@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam String address) {
        Page<Posts> postsPage = postsSearchService.findPostsByAddress(address, page-1, size);
        List<Posts> postsList = postsPage.getContent();

        List<PostsResponseListDto> postsResponseListDtos = postListService.postsResponseListDtos(postsList);
        return new ResponseEntity<>(new MultiResponseDto<>(postsResponseListDtos,postsPage), HttpStatus.OK);
    }

    @GetMapping("/search-title")
    public ResponseEntity searchByTitle(@RequestParam int page,
                                        @RequestParam int size,
                                        @RequestParam String title) {
        Page<Posts> postsPage = postsSearchService.findPostsByTitle(title, page-1, size);
        List<Posts> postsList = postsPage.getContent();

        List<PostsResponseListDto> postsResponseListDtos = postListService.postsResponseListDtos(postsList);
        return new ResponseEntity<>(new MultiResponseDto<>(postsResponseListDtos,postsPage), HttpStatus.OK);
    }

    @GetMapping("/search-tag")
    public ResponseEntity searchByTag(@RequestParam int page,
                                      @RequestParam int size,
                                      @RequestParam String tag) {
        Page<Posts> postsPage = postsSearchService.findByTag(tag, page-1, size);
        List<Posts> postsList = postsPage.getContent();

        List<PostsResponseListDto> postsResponseListDtos = postListService.postsResponseListDtos(postsList);
        return new ResponseEntity<>(new MultiResponseDto<>(postsResponseListDtos,postsPage), HttpStatus.OK);
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

        PostsResponseDto postsResponseDto = postsCombineService.combine(response, reviewPage, roomList);

        return new ResponseEntity<>(postsResponseDto, HttpStatus.OK);
    }
}
