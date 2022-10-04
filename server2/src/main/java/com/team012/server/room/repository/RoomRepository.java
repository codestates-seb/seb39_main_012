package com.team012.server.room.repository;

import com.team012.server.room.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends JpaRepository<Room, Long> {
    @Query("select r from Room r where r.postsId = :id")
    List<Room> findAllByPostsId(@Param("id") Long id);

    @Query("select r from Room r where r.postsId = :postsId and r.size = :size")
    Optional<Room> findByPostsIdAndSize(@Param("postsId")Long postsId,@Param("size") String size);

    @Query("select min(r.price) from Room r where r.postsId = :id")
    Integer findMinPrice(@Param("id")Long id);

}
