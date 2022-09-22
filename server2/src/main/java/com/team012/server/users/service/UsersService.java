package com.team012.server.users.service;

import com.team012.server.company.service.CompanyService;
import com.team012.server.review.entity.Review;
import com.team012.server.review.service.ReviewService;
import com.team012.server.users.dto.CustomerSignUpRequestDto;
import com.team012.server.users.entity.DogCard;
import com.team012.server.users.repository.DogCardRepository;
import com.team012.server.users.repository.UsersRepository;
import com.team012.server.users.dto.CompanySignUpRequestDto;
import com.team012.server.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    // Customer(기본) 회원가입
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

    // 회원 이메일 중복체크
    public Boolean getEmail(String email) {
        Users users = usersRepository.findByEmail(email);
        return users != null;
    }

    // 고객 정보 조회
    public Users getCustomer(Long id) {
        return usersRepository.findById(id).orElseThrow(NullPointerException::new);
    }


}
