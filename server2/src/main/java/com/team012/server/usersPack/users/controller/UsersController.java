package com.team012.server.usersPack.users.controller;

import com.team012.server.company.service.CompanyService;
import com.team012.server.usersPack.users.dto.UsersDto;
import com.team012.server.usersPack.users.entity.Users;
import com.team012.server.usersPack.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
@Validated
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    private final CompanyService companyService;

    // 회사 회원가입
    @PostMapping("/join/company")
    public ResponseEntity postCompany(@Valid @RequestBody UsersDto.CompanyPost dto) {
        Users users = usersService.createCompany(dto);
        Long userId = users.getId();
        companyService.createCompany(dto, userId);

        UsersDto.MessageResponse response = UsersDto.MessageResponse.builder()
                .message("회원가입 완료..!")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 고객 회원가입
    @PostMapping("/join/customer")
    public ResponseEntity postCustomer(@Valid @RequestBody UsersDto.CustomerPost dto) {
        usersService.createCustomer(dto);

        UsersDto.MessageResponse response = UsersDto.MessageResponse.builder()
                .message("회원가입 완료..!")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 이메일 중복확인
    @GetMapping("/check")
    public Boolean checkEmail(@RequestParam String email) {
        return usersService.getEmail(email);
    }

}
