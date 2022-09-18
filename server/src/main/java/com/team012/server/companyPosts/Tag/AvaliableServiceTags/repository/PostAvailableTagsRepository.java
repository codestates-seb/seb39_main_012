package com.team012.server.companyPosts.Tag.AvaliableServiceTags.repository;

import com.team012.server.companyPosts.Tag.AvaliableServiceTags.entity.PostAvailableTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostAvailableTagsRepository extends JpaRepository<PostAvailableTags, Long> {
    @Query("select a from PostAvailableTags a where a.companyPosts.id = :id")
    List<PostAvailableTags> findByCompanyPostsId(Long id);
}
