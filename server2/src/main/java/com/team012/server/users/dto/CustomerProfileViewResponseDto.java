package com.team012.server.users.dto;

import com.team012.server.review.dto.ReviewInfoDto;
import com.team012.server.users.entity.Users;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerProfileViewResponseDto {
    // 고객유저 정보
    private Users users;
    
    private String usersImg;
    // 다녀온 호캉스 내역??? 상태가 다른 호텔값을 따로 뽑아서 하는지????

    // review 관리
    private List<ReviewInfoDto> reviewList;

}
