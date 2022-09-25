package com.team012.server.posts.repository;

import com.team012.server.posts.entity.PostsAvgScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostsAvgScoreRepository extends JpaRepository<PostsAvgScore, Long> {
    List<PostsAvgScore> findByPostsId(Long postsId);
}
