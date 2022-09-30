package com.team012.server.posts.img.controller;

import com.team012.server.common.aws.service.AwsS3Service;
import com.team012.server.posts.img.dto.ImgDto;
import com.team012.server.posts.img.service.PostsImgService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/company/img")
public class ImgController {
    private final PostsImgService postsImgService;

    @PatchMapping("/{img-id}")
    public ResponseEntity patchImg(
            @PathVariable("img-id") Long imgId,
            @RequestPart MultipartFile multipartFile) throws FileUploadException {
        ImgDto imgDto = postsImgService.updateImg(imgId,multipartFile);

        return new ResponseEntity(imgDto, HttpStatus.OK);
    }

    @DeleteMapping("/{img-id}")
    public ResponseEntity deleteImg(@PathVariable("img-id") Long imgId) {
        postsImgService.delete(imgId);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
