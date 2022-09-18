package com.team012.server.companyEtc.service;

import com.team012.server.companyEtc.entity.CompanyRoom;
import com.team012.server.companyEtc.repository.CompanyRoomRepository;
import com.team012.server.companyPosts.repository.CompanyPostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CompanyRoomService {

    private final CompanyRoomRepository companyRoomRepository;
    private final CompanyPostsRepository companyPostsRepository;

    public CompanyRoom save(CompanyRoom companyRoom) {
        return companyRoomRepository.save(companyRoom);
    }

}
