package com.team012.server.posts.Tag.ServiceTag.repository;

import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import com.team012.server.posts.Tag.ServiceTag.entity.ServiceTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostsServiceTagRepository extends JpaRepository<PostsServiceTag, Long> {
    @Query("select a from PostsServiceTag a where a.posts.id = :id")
    List<PostsServiceTag> findByPostsId(@Param("id") Long id);

//    @Query("select a.hashTag from PostsServiceTag a where a.posts.id = :id")
//    List<ServiceTag> findServiceTagByPostsId(@Param("id") Long id);

//    @Query("select p from PostsServiceTag p where p.serviceTag.id = :id")
//    Optional<PostsServiceTag> findByPostsServiceTagId(@Param("id") Long id);
}
