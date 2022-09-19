package com.team012.server.users.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.team012.server.users.dto.UsersDto;
import com.team012.server.users.entity.Users;
import com.team012.server.users.mapper.UsersMapper;
import com.team012.server.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/company")
@Validated
public class UsersController {

    private final UsersService usersService;

    private final UsersMapper mapper;

    private final ObjectMapper objectMapper;

    public UsersController(UsersService usersService, UsersMapper mapper, ObjectMapper objectMapper) {
        this.usersService = usersService;
        this.mapper = mapper;
        this.objectMapper = objectMapper;
    }

    // 회사 회원가입
    @PostMapping("/join")
    public ResponseEntity postCompany(@Valid @RequestBody UsersDto.Post dto) {
        usersService.createCompany(dto);
        UsersDto.Response response = UsersDto.Response.builder()
                .message("회원가입 완료..!")
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 이메일 중복확인
    @PostMapping("/check")
    public ResponseEntity getEmail(@Valid @RequestBody UsersDto.CheckEmail email) {
        // email 있는지 확인
        Users check = usersService.checkEmail(email.getEmail());

        UsersDto.Response response = UsersDto.Response.builder()
                .message("사용가능한 이메일입니다...!")
                .build();

        if (check != null) {
            response.setMessage("이메일이 존재합니다..!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 회사 상세조회 페이지 (회사 정보 + 예약 현황)
    @GetMapping("/profile/{companyId}")
    public ResponseEntity getCompany(@PathVariable("companyId") Long companyId) {
        // 회사의 정보가 있는지 확인
        Users checkCompany = usersService.getCompany(companyId);

        // 정보가 없는 경우
        if (checkCompany == null) throw new NullPointerException("정보가 없습니다.");

        // 정보가 있는 경우 --> 응답 객체 만들어서 반환
        UsersDto.CompanyInfoResponse response = usersService.infoCompany(companyId);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
