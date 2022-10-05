package com.team012.server.posts.Tag.HashTag.controller;


import com.team012.server.posts.Tag.HashTag.dto.HashTagResponseDto;
import com.team012.server.posts.Tag.HashTag.dto.HashTagUpdateDto;
import com.team012.server.posts.Tag.HashTag.entity.HashTag;
import com.team012.server.posts.Tag.HashTag.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/company/hashTag")
public class HashTagController {

    private final TagService tagService;

//    @PatchMapping("/{hashtag-id}")
//    public ResponseEntity<HashTagResponseDto> updateHashTag(@PathVariable("hashtag-id") Long hashtagId,
//                                        @RequestBody HashTagUpdateDto hashTagUpdateDto) {
//        hashTagUpdateDto.setHashTagId(hashtagId);
//        HashTag hashTag = tagService.updateHashTag(hashTagUpdateDto);
//
//        HashTagResponseDto hashTagResponseDto = HashTagResponseDto.builder()
//                .hashTagId(hashTag.getId())
//                .tag(hashTag.getTag())
//                .build();
//
//        return new ResponseEntity<>(hashTagResponseDto, HttpStatus.OK);
//    }

    @DeleteMapping("/{hashtag-id}")
    public ResponseEntity<Void> deleteHashTag(@PathVariable("hashtag-id")Long hashtagId
                                              ) {
        tagService.deleteHashTag(hashtagId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
