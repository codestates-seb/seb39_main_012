package com.team012.server.posts.service;

import com.team012.server.company.entity.Company;
import com.team012.server.company.service.CompanyService;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.Tag.HashTag.service.TagService;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
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

    @Transactional(propagation = Propagation.REQUIRED)
    public PostsResponseDto updatePostResponse(PostsUpdateDto request, List<MultipartFile> files, Long usersId) throws FileUploadException {
        Company company = companyService.getCompany(usersId);
        Long companyId = company.getId();

        Posts response = postsService.update(request, files, companyId);
        List<Room> roomList = roomService.updateRoomList(request.getRoomDtoList(), request.getId());
        System.out.println("# hashTag");

        Long postsId = response.getId();

        tagService.deleteAllHashTag(postsId);
        List<PostsHashTags> hashTagsList =
                tagService.saveCompanyPostsTags(tagService.saveOrFind(request.getHashTag()), response);

        serviceTagService.deleteAllServiceTag(postsId);
        List<PostsServiceTag> serviceTagList =
                serviceTagService.saveCompanyPostsTags(serviceTagService.saveServiceTags(request.getServiceTag()), response);


        System.out.println("# serviceTags");
        return postsCombineService.combine(companyId, response, roomList, hashTagsList,serviceTagList);
    }
}
