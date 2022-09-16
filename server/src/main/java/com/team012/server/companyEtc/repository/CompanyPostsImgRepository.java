package com.team012.server.companyEtc.repository;

import com.team012.server.companyEtc.entity.CompanyPostsImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyPostsImgRepository extends JpaRepository<CompanyPostsImg, Long> {
    @Query("select i from CompanyPostsImg i where i.companyPosts.id = :id")
    List<CompanyPostsImg> findAllByCompanyPostsId(Long id);
}
