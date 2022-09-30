package com.team012.server.posts.converter;

import com.team012.server.company.room.converter.RoomConvert;
import com.team012.server.company.room.dto.RoomResponseDto;
import com.team012.server.company.room.entity.Room;
import com.team012.server.posts.Tag.HashTag.dto.HashTagResponseDto;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.Tag.ServiceTag.dto.ServiceResponseDto;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import com.team012.server.posts.Tag.converter.TagConverter;
import com.team012.server.posts.dto.PostsResponseDto;
import com.team012.server.posts.dto.PostsResponseListDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.img.converter.ConvertToImgDto;
import com.team012.server.posts.img.dto.ImgDto;
import com.team012.server.review.entity.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Component
public class ConvertToPostsResponseDto {

    private final ConvertToImgDto convertToImgDto;
    private final TagConverter tagConverter;
    private final RoomConvert roomConvert;

    public PostsResponseDto createToDto(Long companyId, Posts posts, List<Room> roomList1, List<PostsHashTags> postsHashTags, List<PostsServiceTag>  postsServiceTags) {
        List<ImgDto> imgDtos = convertToImgDto.convertToImgDtos(posts.getPostsImgList());
        List<HashTagResponseDto> hashTags = tagConverter.toHashTagDto(postsHashTags);
        List<ServiceResponseDto> serviceTags = tagConverter.toServiceTagDto(postsServiceTags);
        List<RoomResponseDto> roomResponseDtos = roomConvert.roomDtoList(roomList1);

        return PostsResponseDto.builder()
                .id(posts.getId())
                .title(posts.getTitle())
                .content(posts.getContent())
                .latitude(posts.getLatitude())
                .longitude(posts.getLongitude())
                .address(posts.getAddress())
                .detailAddress(posts.getDetailAddress())
                .companyId(companyId)
                .avgScore(posts.getAvgScore())
                .likesCount(posts.getLikesCount())
                .checkIn(posts.getCheckIn().format(DateTimeFormatter.ofPattern("HH:mm")))
                .checkOut(posts.getCheckOut().format(DateTimeFormatter.ofPattern("HH:mm")))
                .postsImgList(imgDtos)
                .hashTag(hashTags)
                .serviceTag(serviceTags)
                .roomResponseDtos(roomResponseDtos)
                .build();
    }


    public PostsResponseListDto postsResponseListDto(Posts post, Integer minPrice, ImgDto imgDto) {
        return PostsResponseListDto.builder()
        .id(post.getId())
        .title(post.getTitle())
        .address(post.getAddress())
        .avgScore(post.getAvgScore())
        .likesCount(post.getLikesCount())
        .img(imgDto)
        .minPrice(minPrice)
        .build();
    }

    public PostsResponseDto postsResponseDto(Posts posts, List<Review> reviewList, List<Room> roomList) {
        List<ImgDto> imgDtos = convertToImgDto.convertToImgDtos(posts.getPostsImgList());
        List<HashTagResponseDto> hashTagResponseDtos = tagConverter.toHashTagDto(posts.getPostsHashTags());
        List<ServiceResponseDto> serviceResponseDtos = tagConverter.toServiceTagDto(posts.getPostAvailableTags());
        List<RoomResponseDto> roomResponseDtos = roomConvert.roomDtoList(roomList);

        return PostsResponseDto.builder()
                .id(posts.getId())
                .title(posts.getTitle())
                .content(posts.getContent())
                .latitude(posts.getLatitude())
                .longitude(posts.getLongitude())
                .address(posts.getAddress())
                .detailAddress(posts.getDetailAddress())
                .companyId(posts.getCompanyId())
                .avgScore(posts.getAvgScore())
                .checkIn(posts.getCheckIn().format(DateTimeFormatter.ofPattern("HH:mm")))
                .checkOut(posts.getCheckOut().format(DateTimeFormatter.ofPattern("HH:mm")))
                .likesCount(posts.getLikesCount())
                .postsImgList(imgDtos)
                .hashTag(hashTagResponseDtos)
                .serviceTag(serviceResponseDtos)
                .roomResponseDtos(roomResponseDtos)
                .reviewList(reviewList)
                .build();
    }


}
