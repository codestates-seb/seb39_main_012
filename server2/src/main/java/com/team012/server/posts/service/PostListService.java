package com.team012.server.posts.service;

import com.team012.server.company.room.service.RoomService;
import com.team012.server.posts.converter.ConvertToPostsResponseDto;
import com.team012.server.posts.dto.PostsResponseListDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.img.converter.ConvertToImgDto;
import com.team012.server.posts.img.dto.ImgDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class PostListService { //어...이건 컨버터로 가야 하나요 아니면 service로 가야하나요...?
    private final RoomService roomService;
    private final ConvertToImgDto convertToImgDto;
    private final ConvertToPostsResponseDto convertToPostsResponseDto;

    public List<PostsResponseListDto> postsResponseListDtos(List<Posts> posts) {
        List<PostsResponseListDto> postsResponseListDtos = new ArrayList<>();
        for (Posts post : posts) {
            Integer minPrice = roomService.findMinPrice(post.getId());
            ImgDto imgDto = convertToImgDto.convertToImgDto(post.getPostsImgList());

            PostsResponseListDto postsResponseListDto = convertToPostsResponseDto.postsResponseListDto(post, minPrice, imgDto);

            postsResponseListDtos.add(postsResponseListDto);
        }
        return postsResponseListDtos;
    }
}
