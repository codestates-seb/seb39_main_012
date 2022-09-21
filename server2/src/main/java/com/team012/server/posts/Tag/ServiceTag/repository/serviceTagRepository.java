package com.team012.server.posts.Tag.ServiceTag.repository;

import com.team012.server.posts.Tag.ServiceTag.entity.ServiceTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface serviceTagRepository extends JpaRepository<ServiceTag, Long> {
    Optional<ServiceTag> findByTag(String tag);
}
