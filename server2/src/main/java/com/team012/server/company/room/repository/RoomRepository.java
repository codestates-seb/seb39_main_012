package com.team012.server.company.room.repository;

import com.team012.server.company.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("select r from Room r where r.postsId = :id")
    List<Room> findAllByPostsId(@Param("id") Long id);

    List<Room> findByPostsId(Long postsId);

    @Query("select min(r.price) from Room r where r.postsId = :id")
    Integer findMinPrice(@Param("id")Long id);
}
