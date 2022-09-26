package com.team012.server.posts.repository;

import com.team012.server.posts.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    Page<Posts> findByAddressContaining(String address, Pageable pageable);

    Page<Posts> findByTitleContaining(String title, Pageable pageable);

    @Query("select distinct(p) from Posts p join p.postsHashTags h " +
            "where h.hashTag.tag Like %:tag%")
    Page<Posts> findByHashTag(@Param("tag") String tag, Pageable pageable);

//    @Query("select p from Posts p where p.roomCount > (select )")
//    Page<Posts> findByCheckInCheckOut();
}
