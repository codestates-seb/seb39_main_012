package com.team012.server.posts.repository;

import com.team012.server.posts.entity.PostsAvgScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface PostsAvgScoreRepository extends JpaRepository<PostsAvgScore, Long> {
    List<PostsAvgScore> findByPostsId(Long postsId);

}
