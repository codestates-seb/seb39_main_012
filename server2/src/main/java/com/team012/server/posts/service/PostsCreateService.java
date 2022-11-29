package com.team012.server.posts.service;

import com.team012.server.company.entity.Company;
import com.team012.server.company.service.CompanyService;
import com.team012.server.posts.Tag.HashTag.entity.HashTag;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.Tag.HashTag.service.TagService;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import com.team012.server.posts.Tag.ServiceTag.entity.ServiceTag;
import com.team012.server.posts.Tag.ServiceTag.service.ServiceTagService;
import com.team012.server.posts.dto.PostsCreateDto;
import com.team012.server.posts.dto.PostsResponseDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.room.dto.RoomCreateDto;
import com.team012.server.room.entity.Room;
import com.team012.server.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PostsCreateService {

    private final CompanyService companyService;
    private final PostsService postsService;
    private final TagService tagService;
    private final RoomService roomService;
    private final ServiceTagService serviceTagService;
    private final PostsCombineService postsCombineService;

    public PostsResponseDto createPostsResponse(PostsCreateDto postsCreateDto, List<MultipartFile> images, Long usersId){
        Company company = companyService.getCompany(usersId);
        Long companyId = company.getId();

        Posts posts = postsService.save(postsCreateDto, images, companyId);

        List<PostsHashTags> postsHashTags = savePostsHashTags(postsCreateDto, posts);
        List<PostsServiceTag>  postsServiceTags = savePostsServiceTag(postsCreateDto, posts);

        Long postsId = posts.getId();

        List<Room> roomList = saveRoom(postsCreateDto, postsId);

        return postsCombineService.combine(companyId, posts, roomList, postsHashTags, postsServiceTags);
    }

    private List<PostsHashTags> savePostsHashTags(PostsCreateDto postsCreateDto, Posts posts) {
        List<String> hashTag = postsCreateDto.getHashTag();
        List<HashTag> tags = tagService.saveOrFind(hashTag);

        return tagService.saveCompanyPostsTags(tags, posts);
    }

    private List<PostsServiceTag> savePostsServiceTag(PostsCreateDto postsCreateDto, Posts posts) {
        List<String> serviceTag = postsCreateDto.getServiceTag();
        List<ServiceTag> serviceTags = serviceTagService.saveServiceTags(serviceTag);

        return serviceTagService.saveCompanyPostsTags(serviceTags, posts);
    }

    private List<Room> saveRoom(PostsCreateDto postsCreateDto ,long postsId) {
        List<RoomCreateDto> roomList = postsCreateDto.getRoomCreateDtoList();

        roomService.saveList(roomList, postsId);
        return roomService.findAllRoom(postsId);
    }


}
