package com.team012.server.users.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DogCardResponseDto {

    private Long id;
    private String photoImgUrl;
    private String dogName;
    private String type;
    private String gender;
    private Integer age;
    private Double weight;
    private String snackMethod;
    private String bark;
    private String surgery;
    private String bowelTrained;
    private String username;
    private String etc;
}
