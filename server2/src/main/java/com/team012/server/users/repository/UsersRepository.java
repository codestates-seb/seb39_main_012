package com.team012.server.users.repository;

import com.team012.server.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    Users findByCompanyName(String companyName);
}
