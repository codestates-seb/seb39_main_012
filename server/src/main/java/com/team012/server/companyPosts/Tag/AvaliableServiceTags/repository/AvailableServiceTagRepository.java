package com.team012.server.companyPosts.Tag.AvaliableServiceTags.repository;

import com.team012.server.companyPosts.Tag.AvaliableServiceTags.entity.AvailableServiceTags;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvailableServiceTagRepository extends JpaRepository<AvailableServiceTags, Long> {
    Optional<AvailableServiceTags> findByTag(String tag);
}
