package com.team012.server.posts.repository;

import com.team012.server.posts.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    Page<Posts> findByAddressContaining(String address, Pageable pageable);

    Page<Posts> findByTitleContaining(String title, Pageable pageable);

    @Query("select distinct(p) from Posts p join p.postsHashTags h " +
            "where h.hashTag.tag = :tag")
    Page<Posts> findByHashTag(@Param("tag") String tag, Pageable pageable);

    Posts findByCompanyId(Long companyId);

    @Query("select new com.team012.server.posts.repository.RoomPriceDto(r.postsId, min(r.price)) " +
            " from Posts p, Room r where p.id = r.postsId and p.address Like %:address% group by p.id")
    Page<RoomPriceDto> findAllRoomMinPriceAddressContaining(Pageable pageable, String address);

    @Query("select new com.team012.server.posts.repository.RoomPriceDto(r.postsId, min(r.price)) " +
            " from Posts p, Room r where p.id = r.postsId and p.title Like %:title% group by p.id")
    Page<RoomPriceDto> findAllRoomMinPriceTitleContaining(Pageable pageable, String title);


    @Query("select new com.team012.server.posts.repository.RoomPriceDto(r.postsId, min(r.price)) " +
            " from Posts p, Room r where p.id = r.postsId group by p.id")
    Page<RoomPriceDto> findAllRoomMinPrice(Pageable pageable);
}
