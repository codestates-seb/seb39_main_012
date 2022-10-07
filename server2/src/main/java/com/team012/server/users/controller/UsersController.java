package com.team012.server.users.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.team012.server.common.aws.service.AwsS3Service;
import com.team012.server.common.config.filter.JwtProperties;
import com.team012.server.common.config.userDetails.PrincipalDetails;
import com.team012.server.users.dto.*;
import com.team012.server.users.entity.Users;
import com.team012.server.users.service.PasswordChangeService;
import com.team012.server.users.service.UsersManageCompanyService;
import com.team012.server.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Random;

@RestController
@RequestMapping("/v1/users")
@Validated
@RequiredArgsConstructor
@Slf4j
public class UsersController {

    private final UsersService usersService;

    private final UsersManageCompanyService usersManageCompanyService;

    private final AwsS3Service awsS3Service;


    private final PasswordChangeService passwordChangeService;

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

    // 유저 정보 수정 API
    @PatchMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                         @RequestPart(value = "dto") CustomerUpdateRequestDto dto,
                                         @RequestPart(value = "file") MultipartFile file) {
        Long userId = principalDetails.getUsers().getId();

        String imgUrl = awsS3Service.singleUploadFile(file);

        usersService.updateCustomer(userId, dto, imgUrl);

        // 토큰을 다시 발행
        String jwtToken = JWT.create()
                .withSubject(principalDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (JwtProperties.EXPIRATION_TIME)))
                .withClaim("id", principalDetails.getUsers().getId())
                .withClaim("email", principalDetails.getUsers().getEmail())
                .withClaim("username", principalDetails.getUsers().getUsername())
                .sign(Algorithm.HMAC512(JwtProperties.SECRET));

        UsersMessageResponseDto response =
                UsersMessageResponseDto
                        .builder()
                        .etc("Bearer " + jwtToken)
                        .message("수정 완료..!")
                        .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 비밀 번호 수정 API
    @PatchMapping("/update/password")
    public ResponseEntity updatePassword(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                         @RequestBody ChangePasswordDto dto) {
        Users findUsers = principalDetails.getUsers();

        boolean checkPassword = passwordChangeService.changePassword(dto, findUsers);

        if (checkPassword) return new ResponseEntity<>("비밀번호를 다시 확인해주세요.", HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>("수정완료..!", HttpStatus.OK);
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
