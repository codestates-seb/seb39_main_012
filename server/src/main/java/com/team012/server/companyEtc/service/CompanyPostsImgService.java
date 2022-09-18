package com.team012.server.companyEtc.service;

import com.team012.server.companyEtc.entity.CompanyPostsImg;
import com.team012.server.companyEtc.repository.CompanyPostsImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyPostsImgService {
    private final CompanyPostsImgRepository imgRepository;

    public List<CompanyPostsImg> saveImgList(List<CompanyPostsImg> imgList) {
        List<CompanyPostsImg> list = new ArrayList<>();
        for(CompanyPostsImg img : imgList) {
            list.add(imgRepository.save(img));
        }
        return list;
    }
}
