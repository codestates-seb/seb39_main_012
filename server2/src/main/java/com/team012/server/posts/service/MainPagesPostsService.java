package com.team012.server.posts.service;

import com.team012.server.posts.dto.PostsResponseListDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.img.converter.PostsImgConverter;
import com.team012.server.posts.img.dto.ImageDto;
import com.team012.server.posts.repository.RoomPriceDto;
import com.team012.server.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class MainPagesPostsService {
    private final PostsImgConverter postsImgConverter;

    public List<PostsResponseListDto> postsResponseListDtos(List<Posts> posts, List<RoomPriceDto> roomList) {
        List<PostsResponseListDto> postsResponseListDtos = new ArrayList<>();
        for (int i = 0; i< posts.size();i++) {
            ImageDto imageDto = postsImgConverter.convertToImgDto(posts.get(i).getPostsImgList());

            PostsResponseListDto postsResponseListDto = PostsResponseListDto.builder()
                    .id(posts.get(i).getId())
                    .title(posts.get(i).getTitle())
                    .avgScore(posts.get(i).getAvgScore())
                    .likesCount(posts.get(i).getLikesCount())
                    .address(posts.get(i).getAddress())
                    .minPrice(roomList.get(i).getPrice())
                    .img(imageDto)
                    .build();

            postsResponseListDtos.add(postsResponseListDto);
        }

        return postsResponseListDtos;
    }

}
