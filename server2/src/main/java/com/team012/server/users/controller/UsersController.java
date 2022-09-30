package com.team012.server.users.controller;

import com.team012.server.users.dto.CustomerSignUpRequestDto;
import com.team012.server.users.dto.UsersMessageResponseDto;
import com.team012.server.users.entity.Users;
import com.team012.server.users.service.UsersManageCompanyService;
import com.team012.server.users.dto.CompanySignUpRequestDto;
import com.team012.server.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Random;

@RestController
@RequestMapping("/v1/users")
@Validated
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UsersService usersService;

    private final UsersManageCompanyService usersManageCompanyService;

    // 회사 회원가입
    @PostMapping("/join/company")
    public ResponseEntity postCompany(@Valid @RequestBody CompanySignUpRequestDto dto) {
        usersManageCompanyService.createCompany(dto);
        UsersMessageResponseDto response = UsersMessageResponseDto
                .builder()
                .message("회원가입 완료..!")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 고객 회원가입
    @PostMapping("/join/customer")
    public ResponseEntity postCustomer(@Valid @RequestBody CustomerSignUpRequestDto dto) {
        usersService.createCustomer(dto);
        UsersMessageResponseDto response = UsersMessageResponseDto
                .builder()
                .message("회원가입 완료..!")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 이메일 중복확인
    @GetMapping("/check")
    public Boolean checkEmail(@RequestParam String email) {
        return usersService.getEmail(email);
    }

    //////////////////// 추가 ///////////////////

    // 이메일 찾기
    @GetMapping("/find/email")
    public ResponseEntity findEmailUser(@RequestParam String phone) {
        Users users = usersService.findByEmail(phone);
        if (users == null) return new ResponseEntity<>("다시 입력해주세요", HttpStatus.NOT_FOUND);
        UsersMessageResponseDto response = UsersMessageResponseDto
                .builder()
                .message("찾으시는 이메일입니다. " + users.getEmail())
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 비밀번호 찾기
    @GetMapping("/find/password")
    public ResponseEntity findEmailPassword(@RequestParam String email) {
        Users users = usersService.findByPassword(email);
        if (users == null) return new ResponseEntity<>("다시 입력해주세요", HttpStatus.NOT_FOUND);

        // 임시 비밀번호 만들기
        byte[] array = new byte[7];
        new Random().nextBytes(array);
        String exPass = new String(array, StandardCharsets.UTF_8);
        log.info("exPass : {}", exPass);

        // 임시 비밀번호 저장
        users.setPassword(exPass);
        usersService.setExPassword(users);

        // 임시 비밀번호 불러오기
        String findPassword = users.getPassword();
        UsersMessageResponseDto response = UsersMessageResponseDto
                .builder()
                .message("찾으시는 비밀번호입니다. " + findPassword + " 꼭 비밀번호를 변경해주세요.")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
