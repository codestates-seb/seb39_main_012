package com.team012.server.posts.controller;

import com.team012.server.company.entity.Company;
import com.team012.server.company.room.dto.RoomDto;
import com.team012.server.company.room.entity.Room;
import com.team012.server.company.room.service.RoomService;
import com.team012.server.company.service.CompanyService;
import com.team012.server.posts.Tag.HashTag.service.TagService;
import com.team012.server.posts.Tag.ServiceTag.service.ServiceTagService;
import com.team012.server.posts.dto.PostsDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.mapper.PostsMapper;
import com.team012.server.posts.service.PostsSearchService;
import com.team012.server.posts.service.PostsService;
import com.team012.server.review.entity.Review;
import com.team012.server.review.service.ReviewService;
import com.team012.server.utils.config.userDetails.PrincipalDetails;
import com.team012.server.utils.response.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/v1/company/posts")
@RestController
public class PostsController {

    private final CompanyService companyService;
    private final PostsService postsService;
    private final ReviewService reviewService;
    private final RoomService roomService;
    private final TagService tagService;
    private final ServiceTagService serviceTagService;
    private final PostsMapper mapper;

    @PostMapping("/create") //@AuthenticationPrincipal PrincipalDetails principal가 없으므로 일단 dto에 companyId 포함시킴
    public ResponseEntity create(@RequestPart(value = "request") @Valid PostsDto.PostDto request,
                                 @RequestPart(value = "file") List<MultipartFile> file,
                                 @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getUsers().getId();
        // 회사정보 --> posts 에 넣어줘야 한다..
        Company company = companyService.getCompany(userId);
        Long companyId = company.getId();

        List<String> hashTag = request.getHashTag();
        List<String> serviceTag = request.getServiceTag();

        List<RoomDto.PostDto> roomList = request.getRoomDtoList();

        Posts response = postsService.save(request, file, companyId);

        roomService.saveList(roomList, response.getId());

        tagService.saveCompanyPostsTags(tagService.saveOrFind(hashTag), response);
        serviceTagService.saveCompanyPostsTags(serviceTagService.saveOrFind(serviceTag), response);
        List<Room> roomList1 = roomService.findAllRoom(response.getId());

        return new ResponseEntity<>(mapper.postsToResponseDto(response, roomList1), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id,
                                 @RequestPart(value = "request") PostsDto.PatchDto request,
                                 @RequestPart(value = "file", required = false) List<MultipartFile> file,
                                 @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getUsers().getId();

        // 회사정보 --> posts 에 넣어줘야 한다..
        Company company = companyService.getCompany(userId);
        Long companyId = company.getId();

        request.setId(id);
        List<String> hashTag = request.getHashTag();
        List<String> serviceTag = request.getServiceTag();
        List<RoomDto.PostDto> roomList = request.getRoomDtoList();

        Posts response = postsService.update(request, file, companyId);

        tagService.deleteCompanyPostsTags(response.getId());
        serviceTagService.deletePostAvailableTags(response.getId());

        tagService.saveCompanyPostsTags(tagService.saveOrFind(hashTag), response);
        serviceTagService.saveCompanyPostsTags(serviceTagService.saveOrFind(serviceTag), response);

        roomService.deleteAll(response.getId());
        List<Room> roomList1 = roomService.saveList(roomList, response.getId());

        return new ResponseEntity<>(mapper.postsToResponseDto(response, roomList1), HttpStatus.OK);
    }

    // 게시판 상세조회
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id,
                              @RequestParam Integer page,
                              @RequestParam Integer size) {
        Posts response = postsService.findById(id);

        // 작성된 리뷰 리스트 페이징처리 해서 넣어주기
        List<Review> reviewPage = reviewService.findByPage(page - 1, size).getContent();
        List<Room> roomList = roomService.findAllRoom(id);

        return new ResponseEntity<>(mapper.postsToPostsViewDto(response, roomList, reviewPage), HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id,
                                 @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUsers().getId();

        // 회사정보 --> posts 에 넣어줘야 한다..
        Company company = companyService.getCompany(userId);

        postsService.delete(id, company.getId());
        roomService.deleteAll(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
