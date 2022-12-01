package com.team012.server.posts.controller;

import com.team012.server.common.response.MultiResponseDto;
import com.team012.server.posts.dto.PostsResponseDto;
import com.team012.server.posts.dto.PostsResponseListDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.repository.RoomPriceDto;
import com.team012.server.posts.service.MainPagesPostsService;
import com.team012.server.posts.service.PostsCombineService;
import com.team012.server.posts.service.PostsSearchService;
import com.team012.server.posts.service.PostsService;
import com.team012.server.review.dto.ReviewPostsResponse;
import com.team012.server.review.service.ReviewService;
import com.team012.server.room.entity.Room;
import com.team012.server.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/posts")
@RestController
public class MainPagesController {

    private final PostsSearchService postsSearchService;
    private final PostsService postsService;
    private final RoomService roomService;
    private final ReviewService reviewService;
    private final MainPagesPostsService mainPagesPostsService;
    private final PostsCombineService postsCombineService;

    //     메인페이지 조회 (별점 순으로 기준해서 정렬)
    @GetMapping
    public ResponseEntity gets(@RequestParam int page,
                               @RequestParam int size) {
        // avgScore 기준으로 정렬된 페이징 처리
        Page<Posts> postsPage = postsSearchService.findAll(page - 1, size);
        Page<RoomPriceDto> roomPriceDtos = postsSearchService.findAllRoomPrice(page - 1, size);

        List<Posts> postsList = postsPage.getContent();
        List<RoomPriceDto> roomPriceDtos1 = roomPriceDtos.getContent();

        List<PostsResponseListDto> postsResponseListDtos = mainPagesPostsService.postsResponseListDtos(postsList, roomPriceDtos1);
        return new ResponseEntity<>(new MultiResponseDto<>(postsResponseListDtos, postsPage), HttpStatus.OK);
    }


    @GetMapping("/search-address")
    public ResponseEntity searchByAddress(@RequestParam int page,
                                          @RequestParam int size,
                                          @RequestParam String address) {
        Page<Posts> postsPage = postsSearchService.findPostsByAddress(address, page - 1, size);
        List<Posts> postsList = postsPage.getContent();

        Page<RoomPriceDto> roomPriceDtos = postsSearchService.findAllRoomPriceAddress(page - 1, size, address);
        List<RoomPriceDto> roomPriceDtos1 = roomPriceDtos.getContent();

        List<PostsResponseListDto> postsResponseListDtos = mainPagesPostsService.postsResponseListDtos(postsList, roomPriceDtos1);
        return new ResponseEntity<>(new MultiResponseDto<>(postsResponseListDtos, postsPage), HttpStatus.OK);
    }

    @GetMapping("/search-title")
    public ResponseEntity searchByTitle(@RequestParam int page,
                                        @RequestParam int size,
                                        @RequestParam(required = false) String title,
                                        @RequestParam(required = false) String contents) {

        Page<Posts> postsPage = postsSearchService.findPostsByTitleOrContents(title, contents,page - 1, size);
        List<Posts> postsList = postsPage.getContent();

        Page<RoomPriceDto> roomPriceDtos = postsSearchService.findAllRoomPriceByTitleOrContents(page - 1, size, title, contents);
        List<RoomPriceDto> roomPriceDtos1 = roomPriceDtos.getContent();

        List<PostsResponseListDto> postsResponseListDtos = mainPagesPostsService.postsResponseListDtos(postsList, roomPriceDtos1);
        return new ResponseEntity<>(new MultiResponseDto<>(postsResponseListDtos, postsPage), HttpStatus.OK);
    }

    @GetMapping("/search-tag")
    public ResponseEntity searchByTag(@RequestParam int page,
                                      @RequestParam int size,
                                      @RequestParam String tag) {
        Page<Posts> postsPage = postsSearchService.findByHashTag(tag, page - 1, size);
        List<Posts> postsList = postsPage.getContent();

        Page<RoomPriceDto> roomPriceDtos = postsSearchService.findAllRoomPrice(page - 1, size,tag);
        List<RoomPriceDto> roomPriceDtos1 = roomPriceDtos.getContent();

        List<PostsResponseListDto> postsResponseListDtos = mainPagesPostsService.postsResponseListDtos(postsList, roomPriceDtos1);
        return new ResponseEntity<>(new MultiResponseDto<>(postsResponseListDtos, postsPage), HttpStatus.OK);
    }

    //로그인 없이도 조회 가능
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id,
                              @RequestParam Integer page,
                              @RequestParam Integer size) {
        Posts response = postsService.findById(id);

        List<ReviewPostsResponse> reviewPostsResponses = reviewService.getByPage(page, size, id);
        List<Room> roomList = roomService.findAllRoom(id);

        PostsResponseDto postsResponseDto = postsCombineService.combine(response, reviewPostsResponses, roomList);

        return new ResponseEntity<>(postsResponseDto, HttpStatus.OK);
    }
}
