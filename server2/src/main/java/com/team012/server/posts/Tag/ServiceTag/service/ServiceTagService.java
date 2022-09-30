package com.team012.server.posts.Tag.ServiceTag.service;

import com.team012.server.posts.Tag.ServiceTag.dto.ServiceTagUpdateDto;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import com.team012.server.posts.Tag.ServiceTag.entity.ServiceTag;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.Tag.ServiceTag.repository.PostsServiceTagRepository;
import com.team012.server.posts.Tag.ServiceTag.repository.serviceTagRepository;
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

    public List<ServiceTag> saveServiceTags(List<String> postsTags) {
        List<ServiceTag> list =  postsTags.stream().map(tag -> {
                ServiceTag serviceTag = ServiceTag.builder()
                        .tag(tag)
                        .build();
                return serviceTag;
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

    public ServiceTag updateServiceTag(ServiceTagUpdateDto serviceTagUpdateDto) {
        Optional<ServiceTag> serviceTag = serviceTagRepository.findById(serviceTagUpdateDto.getServiceTagId());
        ServiceTag findTag = serviceTag.orElseThrow(() -> new RuntimeException("serviceTag not found"));

        Optional.ofNullable(serviceTagUpdateDto.getTag()).ifPresent(findTag::setTag);
        serviceTagRepository.save(findTag);

        return  findTag;
    }

    public void deleteServiceTag(Long serviceTagId) {
        Optional<ServiceTag> serviceTag = serviceTagRepository.findById(serviceTagId);
        ServiceTag findTag = serviceTag.orElseThrow(() -> new RuntimeException("serviceTag not found"));

        serviceTagRepository.delete(findTag);
    }

    public void deletePostAvailableTags(Long posts) {
        List<PostsServiceTag> postsTags = postsServiceTagRepository.findByPostsId(posts);

        if(!postsTags.isEmpty()) postsServiceTagRepository.deleteAll(postsTags);
        //else throw new RuntimeException("companyPostsTags not exist");
    }


}
