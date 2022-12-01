package com.team012.server.posts.dto;

import com.team012.server.room.dto.RoomCreateDto;
import lombok.Getter;

import javax.validation.constraints.*;
import java.util.List;

@Getter
public class PostsCreateDto {
    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    private String latitude;

    @NotBlank
    private String longitude;

    @NotBlank
    private String address;

    @NotBlank
    @Pattern(regexp = "(0(2|3(1|2|3)|4(1|2|3)|5(1|2|3|4|5)|6(1|2|3|4)|10))-[^0][0-9]{2,3}-[0-9]{3,4}")
    private String phone;

    @NotBlank
    private String detailAddress;

    @NotBlank
    @Pattern(regexp = "(오전|오후) ([0]?[0-9]|1[0-2]):[0-5][0-9]")
    private String checkInTime;

    @NotBlank
    @Pattern(regexp = "(오전|오후) ([0]?[0-9]|1[0-2]):[0-5][0-9]")
    private String checkOutTime; //추가

    @NotNull
    private List<String> hashTag;

    @NotNull
    private List<String> serviceTag;

    @Min(1)
    private Integer roomCount;

    @NotEmpty
    private List<RoomCreateDto> roomCreateDtoList;
}



