package com.team012.server.companyPosts.Tag.PostsTag.repository;

import com.team012.server.companyPosts.Tag.PostsTag.entity.PostsTags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostsTagsRepository extends JpaRepository<PostsTags, Long> {
    Optional<PostsTags> findByTag(String tag);
}
