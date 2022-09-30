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
    private String detailAddress;
    //0번째는 위도, 1번째는 경도, 2번째는 주소, 3번째는 상세 주소

    @NotBlank
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]")
    private String checkIn;

    @NotBlank
    @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]")
    private String checkOut; //추가

    @NotNull
    private List<String> hashTag;

    @NotNull
    private List<String> serviceTag;

    @Min(1)
    private Integer roomCount;

    @NotEmpty
    private List<RoomCreateDto> roomCreateDtoList;
}
