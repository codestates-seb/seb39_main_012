package com.team012.server.posts.Tag.HashTag.service;

import com.team012.server.common.exception.BusinessLogicException;
import com.team012.server.common.exception.ExceptionCode;
import com.team012.server.posts.Tag.HashTag.dto.HashTagUpdateDto;
import com.team012.server.posts.Tag.HashTag.entity.HashTag;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.Tag.HashTag.repository.HashTagRepository;
import com.team012.server.posts.Tag.HashTag.repository.PostsHashTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Transactional
@RequiredArgsConstructor
@Service
public class TagService {

    private final HashTagRepository hashTagRepository;
    private final PostsHashTagRepository postsHashTagRepository;

    public List<HashTag> saveOrFind(List<String> postsTags) {
        List<HashTag> hashTags =  postsTags.stream().map(tag -> {
            return HashTag.builder()
                    .tag(tag)
                    .build();
        }).collect(Collectors.toList());
        return hashTagRepository.saveAll(hashTags);
    }

    public List<PostsHashTags> saveCompanyPostsTags(List<HashTag> postsTags, Posts posts) {
        List<PostsHashTags> postsHashTags = new ArrayList<>();
        for(HashTag p : postsTags) {
            PostsHashTags cpt = PostsHashTags.builder()
                    .hashTag(p)
                    .posts(posts)
                    .build();
            postsHashTags.add(cpt);
        }
        posts.setPostsHashTags(postsHashTags);
        return postsHashTagRepository.saveAll(postsHashTags);
    }


    public void deleteHashTag(Long hashTagId) {
        Optional<HashTag> hashTag1 = hashTagRepository.findById(hashTagId);
        HashTag findTag = hashTag1.orElseThrow(() -> new BusinessLogicException(ExceptionCode.HASH_TAG_NOT_FOUND));

        hashTagRepository.delete(findTag);
    }

    public void deleteAllHashTag(Long postsId) {
        List<PostsHashTags> postsHashTags = postsHashTagRepository.findByPostsId(postsId);
        if(!postsHashTags.isEmpty()) postsHashTagRepository.deleteAll(postsHashTags);
    }

}
