package com.team012.server.users.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DogCardCreateDto {

    private String dogName;
    private String type;
    private String gender;
    private Integer age;
    private Double weight;
    private String snackMethod;
    private String bark;
    private String surgery;
    private String bowelTrained;
    private String etc;

}
