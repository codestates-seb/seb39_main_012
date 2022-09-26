package com.team012.server.posts.Tag.HashTag.repository;

import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostsHashTagRepository extends JpaRepository<PostsHashTags, Long> {

    @Query("select c from PostsHashTags c where c.posts.id = :id")
    List<PostsHashTags> findByPostsId(@Param("id") Long id);

}
