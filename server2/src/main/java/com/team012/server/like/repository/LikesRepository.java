package com.team012.server.like.repository;

import com.team012.server.like.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    Optional<Likes> findByUsersIdAndPostsId(Long usersId, Long PostsId);

}
