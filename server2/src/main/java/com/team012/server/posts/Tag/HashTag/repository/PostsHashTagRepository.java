package com.team012.server.posts.Tag.HashTag.repository;

import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsHashTagRepository extends JpaRepository<PostsHashTags, Long> {

    @Query("select c from PostsHashTags c where c.posts.id = :id")
    List<PostsHashTags> findByPostsId(Long id);
}
