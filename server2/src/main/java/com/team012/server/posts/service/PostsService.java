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

    public Posts save(PostsCreateDto post, List<MultipartFile> files, Long companyId) {

        LocalTime checkInTime = convertCheckInToTime(post.getCheckInTime());
        LocalTime checkOutTime = convertCheckOutToTime(post.getCheckOutTime());
        validateCheckInCheckOut(checkInTime, checkOutTime);

        Posts posts = Posts.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .companyId(companyId)
                .latitude(post.getLatitude())
                .longitude(post.getLongitude())
                .address(post.getAddress())
                .detailAddress(post.getDetailAddress())
                .phone(post.getPhone())
                .checkInTime(checkInTime)
                .checkOutTime(checkOutTime)
                .build();

        Posts savedPosts = postsRepository.save(posts);

        List<PostsImg> postsImgs = awsS3Service.convertPostImg(files, savedPosts);
        postsImgService.saveAll(postsImgs);

        postsImgs = postsImgService.findByPostsId(savedPosts.getId());
        posts.setPostsImgList(postsImgs);

        return savedPosts;

    }
    public Posts update(PostsUpdateDto post, List<MultipartFile> files, Long companyId) throws FileUploadException {
        Long postsId = post.getId();
        Posts findPosts = findById(postsId);
        log.info(findPosts.getTitle() ,"{}");

        if (!Objects.equals(findPosts.getCompanyId(), companyId)) throw new BusinessLogicException(COMPANY_ID_NOT_MATCHED);

        updatePosts(post, findPosts);
        updateCheckInTime(post, findPosts);
        updateCheckOutTime(post, findPosts);

        validateCheckInCheckOut(findPosts.getCheckInTime(), findPosts.getCheckOutTime());

        updatePostsImages(files,findPosts, postsId);

        return postsRepository.save(findPosts);
    }

    private void updatePosts(PostsUpdateDto post,Posts findPosts) {
        Optional.ofNullable(post.getLatitude()).ifPresent(findPosts::setLatitude);
        Optional.ofNullable(post.getLongitude()).ifPresent(findPosts::setLongitude);
        Optional.ofNullable(post.getAddress()).ifPresent(findPosts::setAddress);
        Optional.ofNullable(post.getDetailAddress()).ifPresent(findPosts::setDetailAddress);
        Optional.ofNullable(post.getPhone()).ifPresent(findPosts::setPhone);
        Optional.ofNullable(post.getTitle()).ifPresent(findPosts::setTitle);
        Optional.ofNullable(post.getContent()).ifPresent(findPosts::setContent);
    }

    private void updateCheckInTime(PostsUpdateDto post, Posts findPosts) {
        if (StringUtils.hasText(post.getCheckInTime())) {
            LocalTime checkInTime = convertCheckInToTime(post.getCheckInTime());
            findPosts.setCheckInTime(checkInTime);
        }
    }
    private void updateCheckOutTime(PostsUpdateDto post, Posts findPosts) {
        if (StringUtils.hasText(post.getCheckOutTime())) {
            LocalTime checkOutTime = convertCheckOutToTime(post.getCheckOutTime());
            findPosts.setCheckOutTime(checkOutTime);
        }
    }
    private LocalTime convertCheckInToTime(String strCheckIn) {
        strCheckIn = strCheckIn.trim();
        return LocalTime.parse(strCheckIn, DateTimeFormatter.ofPattern("a hh:mm").withLocale(Locale.KOREA));
    }
    private LocalTime convertCheckOutToTime(String strCheckOut) {
        strCheckOut = strCheckOut.trim();
        return LocalTime.parse(strCheckOut, DateTimeFormatter.ofPattern("a hh:mm").withLocale(Locale.KOREA));
    }
    private void validateCheckInCheckOut(LocalTime checkIn, LocalTime checkOut) {
        if(checkOut.isBefore(checkIn)) throw new BusinessLogicException(CHECKIN_CHECKOUT_ERROR);
    }

    private void updatePostsImages(List<MultipartFile> files, Posts findPosts, long postsId) throws FileUploadException {
        if (!CollectionUtils.isEmpty(files)) {
            List<PostsImg> postsImgList = postsImgService.updatePostsImg(files, postsId);
            findPosts.setPostsImgList(postsImgList);
            for (PostsImg postsImg : postsImgList) {
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
