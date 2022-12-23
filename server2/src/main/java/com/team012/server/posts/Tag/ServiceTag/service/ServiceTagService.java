package com.team012.server.posts.Tag.ServiceTag.service;

import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import com.team012.server.posts.Tag.ServiceTag.entity.ServiceTag;
import com.team012.server.posts.Tag.ServiceTag.repository.PostsServiceTagRepository;
import com.team012.server.posts.Tag.ServiceTag.repository.PostsServiceTagJDBCRepository;
import com.team012.server.posts.Tag.ServiceTag.repository.serviceTagRepository;
import com.team012.server.posts.entity.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class ServiceTagService {

    private final serviceTagRepository serviceTagRepository;
    private final PostsServiceTagRepository postsServiceTagRepository;
    private final PostsServiceTagJDBCRepository postsServiceTagJDBCRepository;

    public List<ServiceTag> saveServiceTags(List<String> postsTags) {
        List<ServiceTag> list =  postsTags.stream().map(tag -> {
                ServiceTag serviceTag = ServiceTag.builder()
                        .tag(tag)
                        .build();
                return serviceTag;
        }).collect(Collectors.toList());
        return serviceTagRepository.saveAll(list);
    }

    public void saveCompanyPostsTags(List<ServiceTag> serviceTags, Posts posts) {
        List<PostsServiceTag> postsAvailableTags = new ArrayList<>();
        for(ServiceTag a : serviceTags) {
            PostsServiceTag postsServiceTag = PostsServiceTag.builder()
                    .posts(posts)
                    .serviceTag(a)
                    .build();
            postsAvailableTags.add(postsServiceTag);
        }
        posts.setPostAvailableTags(postsAvailableTags);
        postsServiceTagJDBCRepository.batchInsert(postsAvailableTags);
    }
    public void deleteAllServiceTag(Long postsId) {
        List<PostsServiceTag> postsTags = postsServiceTagRepository.findByPostsId(postsId);

        if(!postsTags.isEmpty()) {
            postsServiceTagRepository.deleteAll(postsTags);
        }
    }

}
