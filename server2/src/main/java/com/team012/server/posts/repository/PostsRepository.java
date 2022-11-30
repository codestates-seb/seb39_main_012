package com.team012.server.posts.repository;

import com.team012.server.posts.entity.Posts;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts, Long> , PostsRepositoryCustom{

    Page<Posts> findByAddressContaining(String address, Pageable pageable);

    Posts findByCompanyId(Long companyId);


    @Query("select new com.team012.server.posts.repository.RoomPriceDto(r.postsId, min(r.price)) " +
            " from Posts p, Room r where p.id = r.postsId group by p.id")
    Page<RoomPriceDto> findAllRoomMinPrice(Pageable pageable);
}
