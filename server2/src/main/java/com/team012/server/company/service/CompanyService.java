package com.team012.server.company.service;

import com.team012.server.company.entity.Company;
import com.team012.server.company.repository.CompanyRepository;
import com.team012.server.users.dto.CompanySignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company createCompany(CompanySignUpRequestDto dto, Long userId) {
        Company savedCompany =
                Company.builder()
                        .companyName(dto.getCompanyName())
                        .ceo(dto.getCeo())
                        .address(dto.getAddress())
                        .detailAddress(dto.getDetailAddress())
                        .userId(userId)
                        .build();

        return companyRepository.save(savedCompany);
    }

    // companyRepository 에 findByUserId(Long userId) 메서드 추가
    @Transactional(readOnly = true)
    public Company getCompany(Long userId) {
        Company findCompany = companyRepository.findByUserId(userId);
        if (findCompany == null) throw new RuntimeException("Company가 없습니다.");

        return findCompany;
    }
}
