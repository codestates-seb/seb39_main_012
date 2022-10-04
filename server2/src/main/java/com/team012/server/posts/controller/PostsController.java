package com.team012.server.posts.controller;

import com.team012.server.common.config.userDetails.PrincipalDetails;
import com.team012.server.company.entity.Company;
import com.team012.server.room.service.RoomService;
import com.team012.server.company.service.CompanyService;
import com.team012.server.posts.dto.PostsCreateDto;
import com.team012.server.posts.dto.PostsResponseDto;
import com.team012.server.posts.dto.PostsUpdateDto;
import com.team012.server.posts.service.PostsCreateService;
import com.team012.server.posts.service.PostsService;
import com.team012.server.posts.service.PostsUpdateService;
import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
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
@Slf4j
public class PostsController {

    private final CompanyService companyService;
    private final PostsService postsService;
    private final RoomService roomService;
    public final PostsCreateService postsCreateService;
    public final PostsUpdateService postsUpdateService;

    @PostMapping("/create") //@AuthenticationPrincipal PrincipalDetails principal가 없으므로 일단 dto에 companyId 포함시킴
    public ResponseEntity create(@RequestPart(value = "request") @Valid PostsCreateDto request,
                                 @RequestPart(value = "file") List<MultipartFile> file,
                                 @AuthenticationPrincipal PrincipalDetails principalDetails) throws FileUploadException {

        Long userId = principalDetails.getUsers().getId();
        // 회사정보 --> posts 에 넣어줘야 한다..
        PostsResponseDto responseDto = postsCreateService.createPostsResponse(request, file, userId);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long id,
                                 @RequestPart(value = "request") PostsUpdateDto request,
                                 @RequestPart(value = "file", required = false) List<MultipartFile> file,
                                 @AuthenticationPrincipal PrincipalDetails principalDetails) throws FileUploadException {

        Long userId = principalDetails.getUsers().getId(); // -> id : 2
        request.setId(id); // -> id : 1

        PostsResponseDto postsResponseDto = postsUpdateService.updatePostResponse(request, file, userId);

        return new ResponseEntity<>(postsResponseDto, HttpStatus.OK);
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
