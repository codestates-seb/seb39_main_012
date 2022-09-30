package com.team012.server.posts.dto;

import com.team012.server.company.room.dto.RoomResponseDto;
import com.team012.server.posts.Tag.HashTag.dto.HashTagResponseDto;
import com.team012.server.posts.Tag.ServiceTag.dto.ServiceResponseDto;
import com.team012.server.posts.img.dto.ImgDto;
import com.team012.server.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String latitude;
    private String longitude;
    private String address;
    private String detailAddress;
    private Long companyId;
    private Double avgScore; // 평균 별점
    private Integer likesCount;
    private String checkIn;
    private String checkOut; //추가
//    private List<Review> reviewList; // 리뷰 리스트 최신 아이디 순
    private List<ImgDto> postsImgList;
    private List<HashTagResponseDto> hashTag;
    private List<ServiceResponseDto> serviceTag;
    private List<RoomResponseDto> roomResponseDtos;

    private List<Review> reviewList;

    public void setHashTag(List<HashTagResponseDto> hashTag) {
        this.hashTag = hashTag;
    }

    public void setServiceTag(List<ServiceResponseDto> serviceTag) {
        this.serviceTag = serviceTag;
    }

    @Builder(builderClassName = "builder", builderMethodName = "builder")
    public PostsResponseDto(Long id, String title, String content, String latitude, String longitude, String address, String detailAddress, Long companyId, Double avgScore, Integer likesCount, String checkIn, String checkOut, List<ImgDto> postsImgList, List<HashTagResponseDto> hashTag, List<ServiceResponseDto> serviceTag, List<RoomResponseDto> roomResponseDtos, List<Review> reviewList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.detailAddress = detailAddress;
        this.companyId = companyId;
        this.avgScore = avgScore;
        this.likesCount = likesCount;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.postsImgList = postsImgList;
        this.hashTag = hashTag;
        this.serviceTag = serviceTag;
        this.roomResponseDtos = roomResponseDtos;
        this.reviewList = reviewList;
    }

    @Builder(builderClassName = "updateBuilder", builderMethodName = "update")
    public PostsResponseDto(Long id, String title, String content, String latitude, String longitude, String address, String detailAddress,Long companyId, Double avgScore, Integer likesCount, String checkIn, String checkOut, List<ImgDto> postsImgList, List<Review> reviewList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.detailAddress = detailAddress;
        this.companyId = companyId;
        this.avgScore = avgScore;
        this.likesCount = likesCount;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.postsImgList = postsImgList;
        this.reviewList = reviewList;
    }

}
