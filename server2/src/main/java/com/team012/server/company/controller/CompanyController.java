package com.team012.server.company.controller;

import com.team012.server.company.dto.CompanyProfileResponseDto;
import com.team012.server.company.entity.Company;
import com.team012.server.company.service.CompanyInfoService;
import com.team012.server.company.service.CompanyService;
import com.team012.server.users.entity.Users;
import com.team012.server.utils.config.userDetails.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyInfoService companyInfoService;

    @GetMapping("/profile")
    public ResponseEntity profileCompany(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                         Integer page,
                                         Integer size) {
        // 로그인한 사장님 정보
        Users findUsers = principalDetails.getUsers();

        // 회사정보
        Long userId = findUsers.getId();
        Company findCompany = companyService.getCompany(userId);
        Long companyId = findCompany.getId();

        CompanyProfileResponseDto response =
                companyInfoService.getProfile(findUsers, userId, companyId, page - 1, size);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
