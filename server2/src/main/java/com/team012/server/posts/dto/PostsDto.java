package com.team012.server.posts.dto;

import com.team012.server.company.room.dto.RoomDto;
import com.team012.server.company.room.entity.Room;
import com.team012.server.posts.img.dto.ImgDto;
import com.team012.server.posts.img.entity.PostsImg;
import com.team012.server.utils.validator.ListSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
public class PostsDto {

    @Getter
    @NoArgsConstructor
    public static class PostDto {
        @NotBlank
        private String title;
        @NotBlank
        private String content;

        private List<String> coordinate; //0번째는 위도, 1번째는 경도, 2번째는 주소, 3번째는 상세 주소

        private List<String> hashTag;
        private List<String> serviceTag;

        @ListSize
        private List<RoomDto.PostDto> roomDtoList;
    }

    @Getter
    @NoArgsConstructor
    public static class PatchDto {

        private Long id;
        private String title;
        private String content;
        private List<String> coordinate;
        private List<String> hashTag;
        private List<String> serviceTag;
        private List<RoomDto.PostDto> roomDtoList;

        public void setId(Long id) {
            this.id = id;
        }
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseDto {
        private Long id;
        private String title;
        private String content;
        private List<String> address;
        private Long companyId;
        private List<PostsImg> postsImgList;
        private List<String> hashTag;
        private List<String> serviceTag;
        private List<Room> roomDtoList;

        public void setHashTag(List<String> hashTag) {
            this.hashTag = hashTag;
        }

        public void setServiceTag(List<String> serviceTag) {
            this.serviceTag = serviceTag;
        }
    }
    //무슨 필드가 문어다리 개수보다 더 많냐

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseListDto {
        private Long id;
        private String title;
        private String address;
        private ImgDto.ResponseListDto img;
        private Integer minPrice;
//        private String roomImg;
    }
}
