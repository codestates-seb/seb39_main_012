package com.team012.server.posts.service;

import com.team012.server.company.entity.Company;
import com.team012.server.company.room.dto.RoomCreateDto;
import com.team012.server.company.room.entity.Room;
import com.team012.server.company.room.service.RoomService;
import com.team012.server.company.service.CompanyService;
import com.team012.server.posts.Tag.HashTag.entity.HashTag;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.Tag.HashTag.service.TagService;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import com.team012.server.posts.Tag.ServiceTag.entity.ServiceTag;
import com.team012.server.posts.Tag.ServiceTag.service.ServiceTagService;
import com.team012.server.posts.converter.ConvertToPostsResponseDto;
import com.team012.server.posts.dto.PostsCreateDto;
import com.team012.server.posts.dto.PostsResponseDto;
import com.team012.server.posts.entity.Posts;
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
    private final ConvertToPostsResponseDto convertToPostsResponseDto;

    public PostsResponseDto createPostsResponse(PostsCreateDto request, List<MultipartFile> file, Long usersId) {
        Company company = companyService.getCompany(usersId);
        Long companyId = company.getId();

        List<String> hashTag = request.getHashTag();
        List<String> serviceTag = request.getServiceTag();

        List<RoomCreateDto> roomList = request.getRoomCreateDtoList();

        Posts posts = postsService.save(request, file, usersId);

        Long postsId = posts.getId();

        List<Room> roomList1 = roomService.saveList(roomList, postsId);

        List<HashTag> tags = tagService.saveOrFind(hashTag);
        List<PostsHashTags> postsHashTags = tagService.saveCompanyPostsTags(tags, posts);

        List<ServiceTag> serviceTags = serviceTagService.saveServiceTags(serviceTag);
        List<PostsServiceTag>  postsServiceTags = serviceTagService.saveCompanyPostsTags(serviceTags, posts);

        return convertToPostsResponseDto.createToDto(companyId, posts, roomList1, postsHashTags, postsServiceTags);
    }


}
