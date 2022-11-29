package com.team012.server.posts.service;

import com.team012.server.common.aws.service.AwsS3Service;
import com.team012.server.common.exception.BusinessLogicException;
import com.team012.server.posts.dto.PostsCreateDto;
import com.team012.server.posts.dto.PostsUpdateDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.img.entity.PostsImg;
import com.team012.server.posts.img.service.PostsImgService;
import com.team012.server.posts.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import static com.team012.server.common.exception.ExceptionCode.*;

@Transactional
@Service
@Slf4j
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;
    private final PostsImgService postsImgService;
    private final AwsS3Service awsS3Service;

    public Posts save(PostsCreateDto postsDto, List<MultipartFile> images, Long companyId) {

        LocalTime checkInTime = convertCheckInToTime(postsDto.getCheckInTime());
        LocalTime checkOutTime = convertCheckOutToTime(postsDto.getCheckOutTime());
        validateCheckInCheckOut(checkInTime, checkOutTime);

        Posts posts = Posts.builder()
                .title(postsDto.getTitle())
                .content(postsDto.getContent())
                .companyId(companyId)
                .latitude(postsDto.getLatitude())
                .longitude(postsDto.getLongitude())
                .address(postsDto.getAddress())
                .detailAddress(postsDto.getDetailAddress())
                .phone(postsDto.getPhone())
                .checkInTime(checkInTime)
                .checkOutTime(checkOutTime)
                .build();

        posts = postsRepository.save(posts);
        savePostsImages(images, posts);

        return posts;
    }

    private void savePostsImages(List<MultipartFile> images, Posts posts) {
        List<PostsImg> postsImgs = awsS3Service.convertPostImg(images, posts);
        postsImgService.saveAll(postsImgs);

        postsImgs = postsImgService.findByPostsId(posts.getId());
        posts.setPostsImgList(postsImgs);
    }
    public Posts update(PostsUpdateDto postsDto, List<MultipartFile> images, Long companyId) throws FileUploadException {
        Long postsId = postsDto.getId();
        Posts findPosts = findById(postsId);
        log.info(findPosts.getTitle() ,"{}");

        if (!Objects.equals(findPosts.getCompanyId(), companyId)) throw new BusinessLogicException(COMPANY_ID_NOT_MATCHED);

        updatePosts(postsDto, findPosts);
        updateCheckInTime(postsDto, findPosts);
        updateCheckOutTime(postsDto, findPosts);

        validateCheckInCheckOut(findPosts.getCheckInTime(), findPosts.getCheckOutTime());

        updatePostsImages(images,findPosts, postsId);

        return postsRepository.save(findPosts);
    }

    private void updatePosts(PostsUpdateDto postsDto,Posts findPosts) {
        Optional.ofNullable(postsDto.getLatitude()).ifPresent(findPosts::setLatitude);
        Optional.ofNullable(postsDto.getLongitude()).ifPresent(findPosts::setLongitude);
        Optional.ofNullable(postsDto.getAddress()).ifPresent(findPosts::setAddress);
        Optional.ofNullable(postsDto.getDetailAddress()).ifPresent(findPosts::setDetailAddress);
        Optional.ofNullable(postsDto.getPhone()).ifPresent(findPosts::setPhone);
        Optional.ofNullable(postsDto.getTitle()).ifPresent(findPosts::setTitle);
        Optional.ofNullable(postsDto.getContent()).ifPresent(findPosts::setContent);
    }

    private void updateCheckInTime(PostsUpdateDto postsDto, Posts findPosts) {
        if (StringUtils.hasText(postsDto.getCheckInTime())) {
            LocalTime checkInTime = convertCheckInToTime(postsDto.getCheckInTime());
            findPosts.setCheckInTime(checkInTime);
        }
    }
    private void updateCheckOutTime(PostsUpdateDto postsDto, Posts findPosts) {
        if (StringUtils.hasText(postsDto.getCheckOutTime())) {
            LocalTime checkOutTime = convertCheckOutToTime(postsDto.getCheckOutTime());
            findPosts.setCheckOutTime(checkOutTime);
        }
    }
    private LocalTime convertCheckInToTime(String checkInTime) {
        checkInTime = checkInTime.trim();
        return LocalTime.parse(checkInTime, DateTimeFormatter.ofPattern("a hh:mm").withLocale(Locale.KOREA));
    }
    private LocalTime convertCheckOutToTime(String checkOutTime) {
        checkOutTime = checkOutTime.trim();
        return LocalTime.parse(checkOutTime, DateTimeFormatter.ofPattern("a hh:mm").withLocale(Locale.KOREA));
    }
    private void validateCheckInCheckOut(LocalTime checkInTime, LocalTime checkOutTime) {
        if(checkOutTime.isBefore(checkInTime)) throw new BusinessLogicException(CHECKIN_CHECKOUT_ERROR);
    }

    private void updatePostsImages(List<MultipartFile> Images, Posts findPosts, long postsId) throws FileUploadException {
        if (!CollectionUtils.isEmpty(Images)) {
            List<PostsImg> postsImages = postsImgService.updatePostsImg(Images, postsId);
            findPosts.setPostsImgList(postsImages);
            for (PostsImg postsImg : postsImages) {
                postsImg.setPosts(findPosts);
            }
        }
    }

    @Transactional(readOnly = true)
    public Posts findById(Long postsId) {
        Optional<Posts> findCompanyPosts
                = postsRepository.findById(postsId);

        return findCompanyPosts.orElseThrow(() -> new BusinessLogicException(POST_NOT_FOUND));
    }

    public void delete(Long postsId, Long companyId) {
        Posts posts = findById(postsId);
        if (!Objects.equals(posts.getCompanyId(), companyId)) throw new BusinessLogicException(COMPANY_ID_NOT_MATCHED);
        awsS3Service.deleteFile(posts.getPostsImgList());
        postsRepository.delete(posts);
    }

    public void savedLikesCount(Posts posts) {
        postsRepository.save(posts);
    }

    public void save(Posts posts) {
        postsRepository.save(posts);
    }


    public Posts findByCompanyId(Long companyId) {
        return postsRepository.findByCompanyId(companyId);
    }




}
