package com.team012.server.companyPosts.service;

import com.team012.server.company.entity.Company;
import com.team012.server.company.repository.CompanyRepository;
import com.team012.server.companyEtc.aws.service.AwsS3Service;
import com.team012.server.companyEtc.entity.CompanyPostsImg;
import com.team012.server.companyEtc.repository.CompanyPostsImgRepository;
import com.team012.server.companyPosts.Tag.AvaliableServiceTags.service.AvaTagsService;
import com.team012.server.companyPosts.Tag.PostsTag.service.TagService;
import com.team012.server.companyPosts.entity.CompanyPosts;
import com.team012.server.companyPosts.repository.CompanyPostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CompanyPostsService {

    private final CompanyPostsRepository companyPostsRepository;
    private final CompanyRepository companyRepository;
    private final CompanyPostsImgRepository imgRepository;
    private final AvaTagsService avaTagsService;
    private final TagService tagService;
    private final AwsS3Service awsS3Service;

    public CompanyPosts save(CompanyPosts companyPosts, Long companyId, List<MultipartFile> files, List<String> postsTags, List<String> aTags) {
        Company mock = Company.builder() //더미 데이터
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
            List<CompanyPostsImg> lists = awsS3Service.convertCompanyPostImg(files);

            companyPosts.setCompanyPostsImgList(lists);
            for (CompanyPostsImg c : lists) {
                c.setCompanyPosts(companyPosts);
            }

            CompanyPosts companyPosts1 = companyPostsRepository.save(companyPosts);

            tagService.saveCompanyPostsTags(tagService.saveOrFind(postsTags),companyPosts1);
            avaTagsService.saveCompanyPostsTags(avaTagsService.saveOrFind(aTags), companyPosts1);

            return companyPosts1;

        } else throw new RuntimeException("CompanyPost already Exist");
    }

    public CompanyPosts update(CompanyPosts companyPosts, Long companyId, List<MultipartFile> multipartFile, List<String> postsTags, List<String> aTags) {
        Long companyPostsId = companyPosts.getId();
        CompanyPosts findCompanyPosts = findById(companyPostsId);

        if(Objects.equals(companyId, findCompanyPosts.getCompany().getId())) {

            Optional.ofNullable(companyPosts.getAddress()).ifPresent(findCompanyPosts::setAddress);
            Optional.ofNullable(companyPosts.getTitle()).ifPresent(findCompanyPosts::setTitle);
            Optional.ofNullable(companyPosts.getContent()).ifPresent(findCompanyPosts::setContent);

            List<CompanyPostsImg> imgList = companyPosts.getCompanyPostsImgList();
            if (imgList != null) {
                List<CompanyPostsImg> companyPostsImgList = imgRepository.findAllByCompanyPostsId(companyPostsId);
                List<CompanyPostsImg> companyPostsImgs1 = awsS3Service.reviseFileV1(companyPostsImgList, multipartFile);
                for (CompanyPostsImg c : companyPostsImgs1) {
                    c.setCompanyPosts(findCompanyPosts);
                }
                findCompanyPosts.setCompanyPostsImgList(companyPostsImgs1);
                imgRepository.deleteAll(companyPostsImgList);
            }
            CompanyPosts companyPosts1 = companyPostsRepository.save(findCompanyPosts);

            tagService.deleteCompanyPostsTags(findCompanyPosts.getId());
            avaTagsService.deletePostAvailableTags(findCompanyPosts.getId());
            tagService.saveCompanyPostsTags(tagService.saveOrFind(postsTags),companyPosts);
            avaTagsService.saveCompanyPostsTags(avaTagsService.saveOrFind(aTags), companyPosts);

            return companyPosts1;
        }
        else throw new RuntimeException("Only Edit Your Own Posts");
    }

    public CompanyPosts findById(Long companyPostsId) {
        Optional<CompanyPosts> findCompanyPosts
                = companyPostsRepository.findById(companyPostsId);

        return findCompanyPosts.orElseThrow(()
                -> new RuntimeException("CompanyPosts Not Found"));
    }

    public Page<CompanyPosts> findByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC,"id");

        return companyPostsRepository.findAll(pageable);
    }

    public void delete(Long companyPostsId) {
        CompanyPosts companyPosts = findById(companyPostsId);
        awsS3Service.deleteFile(companyPosts.getCompanyPostsImgList());

        companyPostsRepository.delete(companyPosts);
    }
}
