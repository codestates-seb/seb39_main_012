package com.team012.server.posts.dto;

import com.team012.server.company.room.dto.RoomDto;
import com.team012.server.company.room.entity.Room;
import com.team012.server.utils.validator.ListSize;
import com.team012.server.posts.img.dto.ImgDto;
import com.team012.server.posts.img.entity.PostsImg;
import com.team012.server.review.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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

        @NotEmpty
        private List<String> coordinate; //0번째는 위도, 1번째는 경도, 2번째는 주소, 3번째는 상세 주소

        @NotBlank
        @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]")
        private String checkIn;

        @NotBlank
        @Pattern(regexp = "([01]?[0-9]|2[0-3]):[0-5][0-9]")
        private String checkOut; //추가

        private List<String> hashTag;

        private List<String> serviceTag;

        @Min(1)
        private Integer roomCount;

        @NotEmpty
        private List<RoomDto.PostDto> roomDtoList;


    }

    @Getter
    @NoArgsConstructor
    public static class PatchDto {

        private Long id;
        private String title;
        private String content;
        @ListSize
        private List<String> coordinate;
        @Pattern(regexp = "(([01]?[0-9]|2[0-3]):[0-5][0-9])*$|")
        private String checkIn;
        @Pattern(regexp = "(([01]?[0-9]|2[0-3]):[0-5][0-9])*$|")
        private String checkOut; //추가
        private List<String> hashTag;
        private List<String> serviceTag;
        private Integer roomCount;
        @ListSize
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
        private Double avgScore; // 평균 별점
        private Integer likesCount;
        private String checkIn;
        private String checkOut; //추가
        private List<Review> reviewList; // 리뷰 리스트 최신 아이디 순
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

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseListDto {
        private Long id;
        private String title;
        private String address;
        private Double avgScore;  // 평균 점수 추가
        private Integer likesCount;
        private ImgDto.ResponseListDto img;
        private Integer minPrice;
    }
}
