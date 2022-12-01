package com.team012.server.room.dto;

import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Validated
public class RoomCreateDto {

    @NotBlank
    private String size;
    @Min(0)
    private Integer price;
    @Min(0)
    private Integer roomCount;

    //갯수
}
