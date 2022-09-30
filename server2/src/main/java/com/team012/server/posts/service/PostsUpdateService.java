package com.team012.server.posts.service;

import com.team012.server.company.entity.Company;
import com.team012.server.room.entity.Room;
import com.team012.server.room.service.RoomService;
import com.team012.server.company.service.CompanyService;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import com.team012.server.posts.dto.PostsResponseDto;
import com.team012.server.posts.dto.PostsUpdateDto;
import com.team012.server.posts.entity.Posts;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class PostsUpdateService {

    private final CompanyService companyService;
    private final PostsService postsService;
    private final RoomService roomService;
    private final PostsCombineService postsCombineService;

    public PostsResponseDto updatePostResponse(PostsUpdateDto request, Long usersId) throws FileUploadException {
        Company company = companyService.getCompany(usersId);
        Long companyId = company.getId();

        Posts response = postsService.update(request, companyId);
        List<Room> roomList = roomService.findAllRoom(response.getId());
        List<PostsHashTags> hashTags = response.getPostsHashTags();
        List<PostsServiceTag> serviceTags = response.getPostAvailableTags();
        return postsCombineService.combine(companyId, response, roomList, hashTags,serviceTags);
    }
}
