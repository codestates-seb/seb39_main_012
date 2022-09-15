package com.team012.server.companyPosts.service;

import com.team012.server.company.entity.Company;
import com.team012.server.company.repository.CompanyRepository;
import com.team012.server.companyPosts.entity.CompanyPosts;
import com.team012.server.companyPosts.repository.CompanyPostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompanyPostsService {

    private final CompanyPostsRepository companyPostsRepository;
    private final CompanyRepository companyRepository;

    public CompanyPosts save(CompanyPosts companyPosts, Long companyId) {
        Company mock = Company.builder()
                .address("서울시 강남구")
                .companyName("코드스테이츠")
                .ceo("이동주")
                .email("dzooo@gmail.com")
                .username("Dzooo")
                .role("ROLE_USER")
                .password("123456789!")
                .build();
        companyRepository.save(mock);
        companyPosts.setCompany(mock);

        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company Not Found"));
        if(company.getCompanyPosts() == null) {
            return companyPostsRepository.save(companyPosts);
        } else throw new RuntimeException("CompanyPost already Exist");
    }

    public CompanyPosts update(CompanyPosts companyPosts, Long companyId) {
        Long companyPostsId = companyPosts.getId();
        CompanyPosts findCompanyPosts = findById(companyPostsId);

        if(Objects.equals(companyId, findCompanyPosts.getCompany().getId())) {

            Optional.ofNullable(companyPosts.getAddress()).ifPresent(findCompanyPosts::setAddress);
            Optional.ofNullable(companyPosts.getTitle()).ifPresent(findCompanyPosts::setTitle);
            Optional.ofNullable(companyPosts.getContent()).ifPresent(findCompanyPosts::setContent);
            Optional.ofNullable(companyPosts.getCompanyServiceTagList()).ifPresent(findCompanyPosts::setCompanyServiceTagList);

            return companyPostsRepository.save(findCompanyPosts);
        }
        else throw new RuntimeException("Only Edit Your Own Posts");
    }

    public CompanyPosts findById(Long companyPostsId) {
        Optional<CompanyPosts> findCompanyPosts
                = companyPostsRepository.findById(companyPostsId);

        return findCompanyPosts.orElseThrow(() -> new RuntimeException("CompanyPosts Not Found"));
    }

    public Page<CompanyPosts> findByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.Direction.DESC,"id");

        return companyPostsRepository.findAll(pageable);
    }

    public void delete(Long companyPostsId) {
        CompanyPosts companyPosts = findById(companyPostsId);
        companyPostsRepository.delete(companyPosts);
    }
}
