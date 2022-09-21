package com.team012.server.company.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CompanyInfoRequestDto {

    @NotBlank(message = "주소를 입력해주세요.")
    private String address;

    @NotBlank(message = "상세주소를 입력해주세요.")
    private String detailAddress;

    @NotBlank(message = "전화번호를 입력해주세요.")
    private String phone;

    @NotBlank(message = "대표 이름을 입력해주세요.")
    private String ceo;

    @NotBlank(message = "회사이름을 입력해주세요.")
    private String companyName;
}
