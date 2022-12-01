package com.team012.server.company.service;

import com.team012.server.company.dto.CompanyProfileResponseDto;
import com.team012.server.company.entity.Company;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.service.PostsService;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.reservation.service.ReservationService;
import com.team012.server.users.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyInfoService {

    private final CompanyService companyService;
    private final PostsService postsService;
    private final ReservationService reservationService;

    public CompanyProfileResponseDto getProfile(Users findUsers, Long usersId, Long companyId, int page, int size) {
        // 1. 회사정보
        Company company = companyService.getCompany(usersId);
        Posts posts = postsService.findByCompanyId(companyId);

        // 2. 예약현황 (예약번호 / 점포명 / 체크인 / 체크아웃 / 예약자)
        Page<Reservation> reservationList = reservationService.getReservation(companyId, page - 1, size);

        CompanyProfileResponseDto dto =
                CompanyProfileResponseDto
                        .builder()
                        .username(findUsers.getUsername())
                        .email(findUsers.getEmail())
                        .phone(findUsers.getPhone())
                        .companyInfo(company)
                        .postsInfo(posts)
                        .reservationPage(reservationList.getContent())
                        .build();

        return dto;
    }


}
