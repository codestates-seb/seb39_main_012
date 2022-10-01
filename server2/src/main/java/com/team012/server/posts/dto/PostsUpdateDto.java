package com.team012.server.posts.dto;

import lombok.Getter;

import javax.validation.constraints.Pattern;

@Getter
public class PostsUpdateDto {

    private Long id;

    private String title;

    private String content;

    @Pattern(regexp = "(([01]?[0-9]|2[0-3]):[0-5][0-9])*$|")
    private String checkIn;

    @Pattern(regexp = "(([01]?[0-9]|2[0-3]):[0-5][0-9])*$|")
    private String checkOut; //추가

    private String latitude;

    private String longitude;

    private String address;

    private String detailAddress;

    private Integer roomCount;

//    private List<String> hashTag;
//    private List<String> serviceTag;
//    private List<RoomCreateDto> roomCreateDtoList;




    public void setId(Long id) {
        this.id = id;
    }

}
