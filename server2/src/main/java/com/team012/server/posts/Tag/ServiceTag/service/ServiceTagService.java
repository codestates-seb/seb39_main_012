package com.team012.server.posts.Tag.ServiceTag.service;

import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import com.team012.server.posts.Tag.ServiceTag.entity.ServiceTag;
import com.team012.server.posts.Tag.ServiceTag.repository.PostsServiceTagRepository;
import com.team012.server.posts.Tag.ServiceTag.repository.serviceTagRepository;
import com.team012.server.posts.entity.Posts;
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
public class ServiceTagService {

    private final serviceTagRepository serviceTagRepository;
    private final PostsServiceTagRepository postsServiceTagRepository;

    public List<ServiceTag> saveOrFind(List<String> postsTags) {
        List<ServiceTag> list =  postsTags.stream().map(tag -> {
            Optional<ServiceTag> tags = serviceTagRepository.findByTag(tag);
            if(tags.isPresent()) {
                return tags.get();
            } else {
                ServiceTag serviceTag = ServiceTag.builder()
                        .tag(tag)
                        .build();
                return serviceTag;
            }
        }).collect(Collectors.toList());
        return serviceTagRepository.saveAll(list);
    }

    public List<PostsServiceTag> saveCompanyPostsTags(List<ServiceTag> serviceTags, Posts posts) {
        List<PostsServiceTag> postsAvailableTags = new ArrayList<>();
        for(ServiceTag a : serviceTags) {
            PostsServiceTag pat = PostsServiceTag.builder()
                    .posts(posts)
                    .serviceTag(a)
                    .build();
            postsAvailableTags.add(pat);
        }
        posts.setPostAvailableTags(postsAvailableTags);
        return postsServiceTagRepository.saveAll(postsAvailableTags);
    }


    public void deletePostAvailableTags(Long posts) {
        List<PostsServiceTag> postsTags = postsServiceTagRepository.findByPostsId(posts);

        if(!postsTags.isEmpty()) postsServiceTagRepository.deleteAll(postsTags);
        //else throw new RuntimeException("companyPostsTags not exist");

    }
}
