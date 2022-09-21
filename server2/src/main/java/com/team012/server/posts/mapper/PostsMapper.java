package com.team012.server.posts.mapper;

import com.team012.server.company.room.entity.Room;
import com.team012.server.company.room.service.RoomService;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import com.team012.server.posts.dto.PostsDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.img.dto.ImgDto;
import com.team012.server.posts.img.entity.PostsImg;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostsMapper {

    default PostsDto.ResponseDto postsToResponseDto(Posts posts, RoomService roomService) {

        List<String> address = List.of(posts.getLatitude(), posts.getLongitude(), posts.getAddress(), posts.getDetailAddress());
        List<Room> roomList = roomService.findAllRoom(posts.getId());
        Long comapnyId = posts.getCompanyId();

        PostsDto.ResponseDto responseDto = PostsDto.ResponseDto.builder()
                .id(posts.getId())
                .title(posts.getTitle())
                .companyId(comapnyId)
                .content(posts.getContent())
                .address(address)
                .postsImgList(posts.getPostsImgList())
                .roomDtoList(roomList)
                .build();

        if (posts.getPostsHashTags().size() != 0) {
            List<PostsHashTags> list = posts.getPostsHashTags();
            List<String> pt = new ArrayList<>();
            for (PostsHashTags c : list) {
                String tags = c.getHashTag().getTag();
                pt.add(tags);
            }
            responseDto.setHashTag(pt);
        }

        if (posts.getPostAvailableTags().size() != 0) {
            List<PostsServiceTag> list = posts.getPostAvailableTags();
            List<String> ast = new ArrayList<>();
            for (PostsServiceTag c : list) {
                String tags = c.getServiceTag().getTag();
                ast.add(tags);
            }
            responseDto.setServiceTag(ast);
        }

        return responseDto;
    }

    default List<PostsDto.ResponseListDto> postsToResponseDtos(List<Posts> postsList, RoomService roomService) {
        List<PostsDto.ResponseListDto> responseListDtos = new ArrayList<>();

        for (Posts p : postsList) {
            PostsImg img = p.getPostsImgList().get(0);
            ImgDto.ResponseListDto imgDto =
                    ImgDto.ResponseListDto.builder()
                            .fileName(img.getFileName())
                            .url(img.getImgUrl())
                            .build();
            String address = p.getAddress();
            Integer minPrice = roomService.findMinPrice(p.getId());

            PostsDto.ResponseListDto responseListDto =
                    PostsDto.ResponseListDto.builder()
                            .id(p.getId())
                            .title(p.getTitle())
                            .address(address)
                            .img(imgDto)
                            .minPrice(minPrice)
                            .build();
            responseListDtos.add(responseListDto);
        }
        return responseListDtos;
    }
}
