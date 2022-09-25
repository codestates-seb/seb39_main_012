package com.team012.server.users.service;

import com.team012.server.company.service.CompanyService;
import com.team012.server.users.dto.CompanySignUpRequestDto;
import com.team012.server.users.dto.CustomerSignUpRequestDto;
import com.team012.server.users.entity.Users;
import com.team012.server.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional
@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;

    private final CompanyService companyService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 이메일 중복체크
    public Boolean getEmail(String email) {
        Users users = usersRepository.findByEmail(email);
        return users != null;
    }

    // Company 회원가입..
    public Users createCompany(CompanySignUpRequestDto dto) {

        // 비밀번호 암호화
        String encPassword = bCryptPasswordEncoder.encode(dto.getPassword());

        // 객체에 반영
        Users savedCompanyUser
                = Users.builder()
                .email(dto.getEmail())
                .password(encPassword)
                .username(dto.getUsername())
                .phone(dto.getPhone())
                .roles("ROLE_COMPANY")
                .build();

        Users users = usersRepository.save(savedCompanyUser);

        companyService.createCompany(dto, users.getId());

        return savedCompanyUser;
    }

    // Customer 회원가입
    public Users createCustomer(CustomerSignUpRequestDto dto) {
        // 회원이 있는지 없는지 체크

        // 비밀번호 암호화
        String encPassword = bCryptPasswordEncoder.encode(dto.getPassword());

        Users savedCustomer
                = Users.builder()
                .email(dto.getEmail())
                .password(encPassword)
                .username(dto.getUsername())
                .phone(dto.getPhone())
                .roles("ROLE_CUSTOMER")
                .build();

        // DB에 저장
        usersRepository.save(savedCustomer);

        return savedCustomer;
    }

    @Transactional(readOnly = true)
    public Users findUsersById(Long usersId) {
        return usersRepository.findById(usersId)
                .orElseThrow(() -> new RuntimeException("member Not found"));
    }



}
