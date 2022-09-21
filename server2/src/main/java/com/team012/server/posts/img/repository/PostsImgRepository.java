package com.team012.server.posts.img.repository;

import com.team012.server.posts.img.entity.PostsImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsImgRepository extends JpaRepository<PostsImg, Long> {
    @Query("select i from PostsImg i where i.posts.id = :id")
    List<PostsImg> findAllByPostsId(Long id);
}
