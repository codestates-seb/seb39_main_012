package com.team012.server.posts.dto;

import com.team012.server.room.dto.RoomCreateDto;
import com.team012.server.room.dto.RoomDto;
import com.team012.server.room.dto.RoomUpdateDto;
import lombok.Getter;

import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
public class PostsUpdateDto {

    private Long id;

    private String title;

    private String content;

    @Pattern(regexp = "(오전|오후) ([0]?[0-9]|1[0-2]):[0-5][0-9]")
    private String checkInTime;

    @Pattern(regexp = "(오전|오후) ([0]?[0-9]|1[0-2]):[0-5][0-9]")
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
