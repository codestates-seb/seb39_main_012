package com.team012.server.posts.Tag.HashTag.service;

import com.team012.server.posts.Tag.HashTag.entity.HashTag;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.Tag.HashTag.repository.HashTagRepository;
import com.team012.server.posts.Tag.HashTag.repository.PostsHashTagRepository;
import com.team012.server.posts.entity.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TagService {

    private final HashTagRepository hashTagRepository;
    private final PostsHashTagRepository postsHashTagRepository;

    public List<HashTag> saveOrFind(List<String> postsTags) {
        return postsTags.stream().map(tag -> {
            Optional<HashTag> tags = hashTagRepository.findByTag(tag);
            if(tags.isPresent()) {
                return tags.get();
            } else {
                HashTag hashTag1 = HashTag.builder()
                        .tag(tag)
                        .build();
                return hashTagRepository.save(hashTag1);
            }
        }).collect(Collectors.toList());
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


    public void deleteCompanyPostsTags(Long postsId) {
        List<PostsHashTags> postsHashTags = postsHashTagRepository.findByPostsId(postsId);

        if(!postsHashTags.isEmpty()) postsHashTagRepository.deleteAll(postsHashTags);
        //else throw new RuntimeException("postsHashTags not exist");

    }



}
