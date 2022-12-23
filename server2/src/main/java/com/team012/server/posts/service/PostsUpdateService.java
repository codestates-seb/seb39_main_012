package com.team012.server.posts.service;

import com.team012.server.company.entity.Company;
import com.team012.server.company.service.CompanyService;
import com.team012.server.posts.Tag.HashTag.entity.HashTag;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.Tag.HashTag.service.TagService;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import com.team012.server.posts.Tag.ServiceTag.entity.ServiceTag;
import com.team012.server.posts.Tag.ServiceTag.service.ServiceTagService;
import com.team012.server.posts.dto.PostsResponseDto;
import com.team012.server.posts.dto.PostsUpdateDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.img.dto.ImgUpdateDto;
import com.team012.server.room.entity.Room;
import com.team012.server.room.service.RoomService;
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
public class PostsUpdateService {

    private final CompanyService companyService;
    private final PostsService postsService;
    private final RoomService roomService;
    private final TagService tagService;
    private final ServiceTagService serviceTagService;
    private final PostsCombineService postsCombineService;

    public void updatePostResponse(PostsUpdateDto postsUpdateDto, List<MultipartFile> images, Long usersId) throws FileUploadException {
        Long companyId = findCompanyId(usersId);

        Posts response = postsService.update(postsUpdateDto, images, companyId);
        List<Room> roomList = roomService.updateRoomList(postsUpdateDto.getRoomDtoList(), postsUpdateDto.getId());

        updateHashTags(postsUpdateDto, response);
        updateServiceTags(postsUpdateDto, response);

//        return postsCombineService.combine(companyId, response, roomList, postsHashTags,postsServiceTags);
    }
    private Long findCompanyId(Long usersId) {
        Company company = companyService.getCompany(usersId);
        return company.getId();
    }

    private void updateHashTags(PostsUpdateDto postsUpdateDto, Posts posts) {
        tagService.deleteAllHashTag(posts.getId());
        List<HashTag> hashTags = tagService.saveOrFind(postsUpdateDto.getHashTag());
        tagService.saveCompanyPostsTags(hashTags, posts);

//        return postsHashTags;
    }

    private void updateServiceTags(PostsUpdateDto postsUpdateDto, Posts posts) {
        serviceTagService.deleteAllServiceTag(posts.getId());
        List<ServiceTag> serviceTags = serviceTagService.saveServiceTags(postsUpdateDto.getServiceTag());

                serviceTagService.saveCompanyPostsTags(serviceTags, posts);

//        return postsServiceTags;
    }

}
