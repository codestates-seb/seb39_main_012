package com.team012.server.usersPack.users.repository;

import com.team012.server.usersPack.users.entity.DogCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogCardRepository extends JpaRepository<DogCard, Long> {
}
