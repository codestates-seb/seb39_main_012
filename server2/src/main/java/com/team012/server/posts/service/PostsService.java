package com.team012.server.posts.service;

import com.team012.server.posts.dto.PostsDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.img.entity.PostsImg;
import com.team012.server.posts.img.repository.PostsImgRepository;
import com.team012.server.posts.repository.PostsRepository;
import com.team012.server.utils.aws.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    private final PostsImgRepository imgRepository;
    private final AwsS3Service awsS3Service;

    public Posts save(PostsDto.PostDto post, List<MultipartFile> files, Long companyId) {
        Posts posts = Posts.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .companyId(companyId)
                .latitude(post.getCoordinate().get(0))
                .longitude(post.getCoordinate().get(1))
                .address(post.getCoordinate().get(2))
                .detailAddress(post.getCoordinate().get(3))
                .build();

//        Optional<Posts> find = postsRepository.findByUsersId(userId);


        List<PostsImg> lists = awsS3Service.convertPostImg(files);

        posts.setPostsImgList(lists);
        for (PostsImg c : lists) {
            c.setPosts(posts);
        }

        return postsRepository.save(posts);

    }

    public Posts update(PostsDto.PatchDto post, List<MultipartFile> multipartFile, Long companyId) {
        Long postsId = post.getId();
        Posts findPosts = findById(postsId);

        if(!Objects.equals(findPosts.getCompanyId(), companyId)) throw new RuntimeException("companyId 일치하지 않음");

        Optional.ofNullable(post.getCoordinate().get(0)).ifPresent(findPosts::setLatitude);
        Optional.ofNullable(post.getCoordinate().get(1)).ifPresent(findPosts::setLongitude);
        Optional.ofNullable(post.getCoordinate().get(2)).ifPresent(findPosts::setAddress);
        Optional.ofNullable(post.getCoordinate().get(3)).ifPresent(findPosts::setDetailAddress);
        Optional.ofNullable(post.getTitle()).ifPresent(findPosts::setTitle);
        Optional.ofNullable(post.getContent()).ifPresent(findPosts::setContent);


        if (multipartFile != null) {

            List<PostsImg> postsImgList = imgRepository.findAllByPostsId(postsId);
            List<PostsImg> postsImgs1 = awsS3Service.reviseFileV1(postsImgList, multipartFile);
            for (PostsImg c : postsImgs1) {
                c.setPosts(findPosts);
            }
            findPosts.setPostsImgList(postsImgs1);
            imgRepository.deleteAll(postsImgList);
        }
        Posts posts1 = postsRepository.save(findPosts);
        return posts1;

    }

    public Posts findById(Long postsId) {
        Optional<Posts> findCompanyPosts
                = postsRepository.findById(postsId);

        return findCompanyPosts.orElseThrow(()
                -> new RuntimeException("Posts Not Found"));
    }

    public Page<Posts> findByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");

        return postsRepository.findAll(pageable);
    }

    public void delete(Long postsId) {
        Posts posts = findById(postsId);
        awsS3Service.deleteFile(posts.getPostsImgList());
        postsRepository.delete(posts);
    }
}
