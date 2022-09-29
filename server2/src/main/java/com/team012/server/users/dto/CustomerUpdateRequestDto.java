package com.team012.server.users.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateRequestDto {

    private String userName;

    private String phone;

}
