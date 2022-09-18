package com.team012.server.companyPosts.Tag.PostsTag.service;

import com.team012.server.companyPosts.Tag.PostsTag.entity.CompanyPostsTags;
import com.team012.server.companyPosts.Tag.PostsTag.entity.PostsTags;
import com.team012.server.companyPosts.Tag.PostsTag.repository.CompanyPostsTagsRepository;
import com.team012.server.companyPosts.Tag.PostsTag.repository.PostsTagsRepository;
import com.team012.server.companyPosts.entity.CompanyPosts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final PostsTagsRepository postsTagsRepository;
    private final CompanyPostsTagsRepository companyPostsTagsRepository;

    public List<PostsTags> saveOrFind(List<String> postsTags) {
        return postsTags.stream().map(tag -> {
            Optional<PostsTags> tags = postsTagsRepository.findByTag(tag);
            if(tags.isPresent()) {
                return tags.get();
            } else {
                PostsTags postsTags1 = PostsTags.builder()
                        .tag(tag)
                        .build();
                return postsTagsRepository.save(postsTags1);
            }
        }).collect(Collectors.toList());
    }

    public List<CompanyPostsTags> saveCompanyPostsTags(List<PostsTags> postsTags, CompanyPosts companyPosts) {
        List<CompanyPostsTags> companyPostsTags = new ArrayList<>();
        for(PostsTags p : postsTags) {
            CompanyPostsTags cpt = CompanyPostsTags.builder()
                    .postsTags(p)
                    .companyPosts(companyPosts)
                    .build();
            companyPostsTags.add(cpt);
        }
        companyPosts.setCompanyPostsTags(companyPostsTags);
        return companyPostsTagsRepository.saveAll(companyPostsTags);
    }


    public void deleteCompanyPostsTags(Long companyPostsId) {
        List<CompanyPostsTags> companyPostsTags = companyPostsTagsRepository.findByCompanyPostsId(companyPostsId);

        if(!companyPostsTags.isEmpty()) companyPostsTagsRepository.deleteAll(companyPostsTags);
        //else throw new RuntimeException("companyPostsTags not exist");

    }



}
