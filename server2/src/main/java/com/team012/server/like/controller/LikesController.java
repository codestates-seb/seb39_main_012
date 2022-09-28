package com.team012.server.like.controller;

import com.team012.server.utils.config.userDetails.PrincipalDetails;
import com.team012.server.like.service.LikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Slf4j
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likeService;

    @PostMapping("/likes/{posts-id}")
    public ResponseEntity addLike(@PathVariable("posts-id") Long postsId
            , @AuthenticationPrincipal PrincipalDetails principal) {

        log.info("===================좋아요 클릭=======================");
        boolean result = false;

        if (principal != null) {
            result = likeService.booleanLike(principal.getUsers().getId(), postsId);
        }

        return result ?
                new ResponseEntity<>("좋아요 추가", HttpStatus.OK) :
                new ResponseEntity<>("좋아요 삭제", HttpStatus.BAD_REQUEST);
    }
}
