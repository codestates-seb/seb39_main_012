package com.team012.server.users.repository;

import com.team012.server.users.entity.DogCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DogCardRepository extends JpaRepository<DogCard, Long> {
    List<DogCard> findByUsers_Id(Long id);
}
