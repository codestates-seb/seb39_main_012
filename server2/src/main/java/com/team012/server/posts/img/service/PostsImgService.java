package com.team012.server.posts.img.service;

import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.img.entity.PostsImg;
import com.team012.server.posts.img.repository.PostsImgJDBCRepository;
import com.team012.server.posts.img.repository.PostsImgRepository;
import com.team012.server.common.aws.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;


@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class PostsImgService {
    private final PostsImgRepository imgRepository;
    private final PostsImgJDBCRepository postsImgJDBCRepository;
    private final AwsS3Service awsS3Service;


    public void saveAll(List<PostsImg> postsImgs) {
        postsImgJDBCRepository.batchInsert(postsImgs);
    }

    @Transactional(readOnly = true)
    public PostsImg findById(Long imgId) {
        return imgRepository.findById(imgId).orElseThrow(()-> new NoSuchElementException("img not found"));
    }

    public List<PostsImg> findByPostsId(Long postsId) {
        return imgRepository.findAllByPostsId(postsId);
    }

    public List<PostsImg> updatePostsImg(List<MultipartFile> files, Long postsId) throws FileUploadException {
        log.info("updatePostImg");

        List<PostsImg> postsImgs = imgRepository.findAllByPostsId(postsId);

        awsS3Service.deleteFile(postsImgs);

        Map<String, String> filenameAndUrl = awsS3Service.getImgUrlAndFilename(files);

        Set<String> set = filenameAndUrl.keySet();
        List<String> filenames = new ArrayList<>(set);

        for (int i = 0; i<postsImgs.size();i++) {
            PostsImg postsImg = postsImgs.get(i);
            String filename = filenames.get(i);

            postsImg.setImgUrl(filenameAndUrl.get(filename));
            postsImg.setFileName(filename);
        }

        return imgRepository.saveAll(postsImgs);



    }


}
