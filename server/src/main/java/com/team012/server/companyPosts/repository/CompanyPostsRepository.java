package com.team012.server.companyPosts.repository;

import com.team012.server.companyPosts.entity.CompanyPosts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyPostsRepository extends JpaRepository<CompanyPosts, Long> {
}
