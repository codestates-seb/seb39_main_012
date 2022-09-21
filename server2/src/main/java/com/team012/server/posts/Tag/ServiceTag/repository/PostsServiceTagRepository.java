package com.team012.server.posts.Tag.ServiceTag.repository;

import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsServiceTagRepository extends JpaRepository<PostsServiceTag, Long> {
    @Query("select a from PostsServiceTag a where a.posts.id = :id")
    List<PostsServiceTag> findByPostsId(Long id);
}
