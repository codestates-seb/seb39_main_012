package com.team012.server.company.dto;

import com.team012.server.company.entity.Company;
import com.team012.server.posts.entity.Posts;
import com.team012.server.reservation.entity.ReservList;
import com.team012.server.users.entity.Users;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

@Slf4j
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyProfileResponseDto {
    private Users usersInfo;
    private Company companyInfo;
    private Posts postsInfo;
    private Page<ReservList> reservListPage;
}
