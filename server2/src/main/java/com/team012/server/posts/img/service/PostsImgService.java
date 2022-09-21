package com.team012.server.posts.img.service;

import com.team012.server.posts.img.entity.PostsImg;
import com.team012.server.posts.img.repository.PostsImgRepository;
import com.team012.server.utils.aws.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostsImgService {
    private final PostsImgRepository imgRepository;
    private final AwsS3Service awsS3Service;

    public List<PostsImg> saveImgList(List<PostsImg> imgList) {
        List<PostsImg> list = new ArrayList<>();
        for(PostsImg img : imgList) {
            list.add(imgRepository.save(img));
        }
        return list;
    }

}
