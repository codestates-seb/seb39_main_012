package com.team012.server.companyPosts.Tag.AvaliableServiceTags.service;

import com.team012.server.companyPosts.Tag.AvaliableServiceTags.entity.AvailableServiceTags;
import com.team012.server.companyPosts.Tag.AvaliableServiceTags.entity.PostAvailableTags;
import com.team012.server.companyPosts.Tag.AvaliableServiceTags.repository.AvailableServiceTagRepository;
import com.team012.server.companyPosts.Tag.AvaliableServiceTags.repository.PostAvailableTagsRepository;
import com.team012.server.companyPosts.entity.CompanyPosts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AvaTagsService {

    private final AvailableServiceTagRepository availableServiceTagRepository;
    private final PostAvailableTagsRepository postAvailableTagsRepository;

    public List<AvailableServiceTags> saveOrFind(List<String> postsTags) {
        return postsTags.stream().map(tag -> {
            Optional<AvailableServiceTags> tags = availableServiceTagRepository.findByTag(tag);
            if(tags.isPresent()) {
                return tags.get();
            } else {
                AvailableServiceTags availableServiceTags = AvailableServiceTags.builder()
                        .tag(tag)
                        .build();
                return availableServiceTagRepository.save(availableServiceTags);
            }
        }).collect(Collectors.toList());
    }

    public List<PostAvailableTags> saveCompanyPostsTags(List<AvailableServiceTags> availableServiceTags, CompanyPosts companyPosts) {
        List<PostAvailableTags> postsAvailableTags = new ArrayList<>();
        for(AvailableServiceTags a : availableServiceTags) {
            PostAvailableTags pat = PostAvailableTags.builder()
                    .companyPosts(companyPosts)
                    .availableServiceTags(a)
                    .build();
            postsAvailableTags.add(pat);
        }
        companyPosts.setPostAvailableTags(postsAvailableTags);
        return postAvailableTagsRepository.saveAll(postsAvailableTags);
    }


    public void deletePostAvailableTags(Long companyPosts) {
        List<PostAvailableTags> companyPostsTags = postAvailableTagsRepository.findByCompanyPostsId(companyPosts);

        if(!companyPostsTags.isEmpty()) postAvailableTagsRepository.deleteAll(companyPostsTags);
        //else throw new RuntimeException("companyPostsTags not exist");

    }
}
