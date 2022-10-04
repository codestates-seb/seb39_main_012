package com.team012.server.posts.img.service;

import com.team012.server.posts.img.converter.PostsImgConverter;
import com.team012.server.posts.img.dto.ImgDto;
import com.team012.server.posts.img.dto.ImgUpdateDto;
import com.team012.server.posts.img.entity.PostsImg;
import com.team012.server.posts.img.repository.PostsImgRepository;
import com.team012.server.common.aws.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class PostsImgService {
    private final PostsImgRepository imgRepository;
    private final AwsS3Service awsS3Service;


    @Transactional(readOnly = true)
    public PostsImg findById(Long imgId) {
        return imgRepository.findById(imgId).orElseThrow(()-> new NoSuchElementException("img not found"));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePostsImg(List<ImgUpdateDto> updateDtos) {
        log.info("updatePostImg");

        List<PostsImg> updatePostsImgList = new ArrayList<>();
        List<PostsImg> oldPostsImgList = new ArrayList<>();

        for (ImgUpdateDto updateDto : updateDtos) {
            PostsImg postsImg = imgRepository.findById(updateDto.getPostsImgId())
                    .orElseThrow(() -> new RuntimeException("img not found"));
            oldPostsImgList.add(postsImg);

            Optional.ofNullable(updateDto.getMultipartFile()).ifPresent(file -> {
                try {
                    List<String> getFilenameAndUrl = awsS3Service.getImgUrlAndFilename(updateDto.getMultipartFile());
                    String filename = getFilenameAndUrl.get(0);
                    String url = getFilenameAndUrl.get(1);

                    postsImg.setFileName(filename);
                    postsImg.setImgUrl(url);
                } catch (FileUploadException e) {
                    throw new RuntimeException(e);
                }
            });
            updatePostsImgList.add(postsImg);

        }
        awsS3Service.deleteUploadedFileAtS3(oldPostsImgList);
        imgRepository.saveAll(updatePostsImgList);
    }


}
