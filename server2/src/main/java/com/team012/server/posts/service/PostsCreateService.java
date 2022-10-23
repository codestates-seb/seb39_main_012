package com.team012.server.posts.service;

import com.team012.server.company.entity.Company;
import com.team012.server.room.dto.RoomCreateDto;
import com.team012.server.room.entity.Room;
import com.team012.server.room.service.RoomService;
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
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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

    public PostsResponseDto createPostsResponse(PostsCreateDto request, List<MultipartFile> file, Long usersId){
        Company company = companyService.getCompany(usersId);
        Long companyId = company.getId();

        List<String> hashTag = request.getHashTag();
        List<String> serviceTag = request.getServiceTag();

        List<RoomCreateDto> roomList = request.getRoomCreateDtoList();

        Posts posts = postsService.save(request, file, companyId);

        Long postsId = posts.getId();

        roomService.saveList(roomList, postsId);
        List<Room> roomList1 = roomService.findAllRoom(postsId);

                List<HashTag> tags = tagService.saveOrFind(hashTag);
        List<PostsHashTags> postsHashTags = tagService.saveCompanyPostsTags(tags, posts);

        List<ServiceTag> serviceTags = serviceTagService.saveServiceTags(serviceTag);
        List<PostsServiceTag>  postsServiceTags = serviceTagService.saveCompanyPostsTags(serviceTags, posts);

        return postsCombineService.combine(companyId, posts, roomList1, postsHashTags, postsServiceTags);
    }


}
