package com.team012.server.posts.Tag.HashTag.repository;

import com.team012.server.posts.Tag.HashTag.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HashTagRepository extends JpaRepository<HashTag, Long> {
    Optional<HashTag> findByTag(String tag);
}
