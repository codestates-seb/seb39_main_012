package com.team012.server.company.dto;

import com.team012.server.company.entity.Company;
import com.team012.server.posts.entity.Posts;
import com.team012.server.reservation.entity.ReservationList;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyProfileResponseDto {
    private String username;
    private String email;
    private String phone;
    private Company companyInfo;
    private Posts postsInfo;
    private List<ReservationList> reservationListPage;
}
