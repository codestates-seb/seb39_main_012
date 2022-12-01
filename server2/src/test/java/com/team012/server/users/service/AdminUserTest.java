package com.team012.server.users.service;

import com.team012.server.company.entity.Company;
import com.team012.server.company.repository.CompanyRepository;
import com.team012.server.users.entity.Users;
import com.team012.server.users.repository.UsersRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class AdminUserTest {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    void adminUser_생성() {
        String password = "adminadmin15!";
        String encPassword = bCryptPasswordEncoder.encode(password);

        Users users = Users.builder()
                .username("admin15")
                .phone("010-1111-2222")
                .email("admin15@admin.com")
                .password(encPassword)
                .roles("ROLE_ADMIN")
                .build();

        Users admin = usersRepository.save(users);
        Company company = Company.builder()
                .companyName("admin")
                .ceo("me")
                .address("서울 서초구 서초대로 396")
                .detailAddress("강남빌딩 20층")
                .userId(admin.getId())
                .build();

        Company company1 = companyRepository.save(company);
    }
}
