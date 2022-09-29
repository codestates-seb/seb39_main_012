package com.team012.server.company.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyUpdateRequestDto {

    private String companyName;
    private String ceo;
    private String address;
    private String detailAddress;
}
