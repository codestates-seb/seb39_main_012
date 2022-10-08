package com.team012.server.users.dto;

import com.team012.server.review.dto.ReviewInfoDto;
import com.team012.server.users.entity.Users;
import lombok.*;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class CustomerProfileViewResponseDto {
    private final Users users;
    private final String usersImg;
    private final List<ReviewInfoDto> reviewList;
}
