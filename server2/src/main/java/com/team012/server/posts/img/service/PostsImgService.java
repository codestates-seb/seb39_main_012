package com.team012.server.posts.img.service;

import com.team012.server.posts.img.converter.PostsImgConverter;
import com.team012.server.posts.img.dto.ImgDto;
import com.team012.server.posts.img.entity.PostsImg;
import com.team012.server.posts.img.repository.PostsImgRepository;
import com.team012.server.common.aws.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
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

    private final PostsImgConverter postsImgConverter;

    public List<PostsImg> saveImgList(List<MultipartFile> file) {
        Map<String, String> map = awsS3Service.getUrlAndFileName(file);

        Set<String> filename = map.keySet();

        return filename.stream().map(f -> PostsImg.builder()
                    .fileName(f)
                    .imgUrl(map.get(f))
                    .build()).collect(Collectors.toList());
    }
    public PostsImg findById(Long imgId) {
        return imgRepository.findById(imgId).orElseThrow(()-> new NoSuchElementException("img not found"));
    }

    public ImgDto updateImg(Long imgId, MultipartFile multipartFile) throws FileUploadException {
        PostsImg postsImg = findById(imgId);
        awsS3Service.deleteFile(postsImg);
        String filename = awsS3Service.originalFileName(multipartFile);
        String url = awsS3Service.singleUploadFile(multipartFile);

        postsImg.setImgUrl(url);
        postsImg.setFileName(filename);
        postsImg =  imgRepository.save(postsImg);
        return postsImgConverter.toDTO(postsImg);

    }

    public void delete(Long imgId) {
        PostsImg postsImg= findById(imgId);
        awsS3Service.deleteFile(postsImg);
        imgRepository.delete(postsImg);
    }


}
