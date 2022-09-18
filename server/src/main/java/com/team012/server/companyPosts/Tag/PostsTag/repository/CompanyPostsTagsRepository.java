package com.team012.server.companyPosts.Tag.PostsTag.repository;

import com.team012.server.companyPosts.Tag.PostsTag.entity.CompanyPostsTags;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyPostsTagsRepository extends JpaRepository<CompanyPostsTags, Long> {

    @Query("select c from CompanyPostsTags c where c.companyPosts.id = :id")
    List<CompanyPostsTags> findByCompanyPostsId(Long id);
}
