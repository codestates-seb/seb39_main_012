package com.team012.server.posts.dto;

import com.team012.server.room.dto.RoomCreateDto;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
public class PostsUpdateDto{

    private Long id;

    private String title;

    private String content;

    private String checkInTime;

    private String checkOutTime; //추가

    private String latitude;

    private String longitude;

    private String address;

    private String detailAddress;

    private String phone;

    private Integer roomCount;

    private List<String> hashTag;

    private List<String> serviceTag;

    private List<RoomCreateDto> roomDtoList;

    public void setId(Long id) {
        this.id = id;
    }

}
