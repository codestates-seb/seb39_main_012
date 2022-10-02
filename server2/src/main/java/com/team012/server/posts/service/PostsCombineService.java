package com.team012.server.posts.service;

import com.team012.server.posts.Tag.HashTag.converter.HashTagConverter;
import com.team012.server.posts.Tag.ServiceTag.converter.ServiceTagConverter;
import com.team012.server.review.dto.ReviewPostsResponse;
import com.team012.server.room.converter.RoomConverter;
import com.team012.server.room.dto.RoomDto;
import com.team012.server.room.entity.Room;
import com.team012.server.posts.Tag.HashTag.dto.HashTagResponseDto;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.Tag.ServiceTag.dto.ServiceResponseDto;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import com.team012.server.posts.dto.PostsResponseDto;
import com.team012.server.posts.dto.PostsResponseListDto;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.img.converter.PostsImgConverter;
import com.team012.server.posts.img.dto.ImgDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@RequiredArgsConstructor
@Component
public class PostsCombineService {

    private final PostsImgConverter postsImgConverter;
    private final HashTagConverter hashTagConverter;
    private final ServiceTagConverter serviceTagConverter;
    private final RoomConverter roomConverter;

    public PostsResponseDto combine(Long companyId, Posts posts, List<Room> roomList1,
                                    List<PostsHashTags> postsHashTags,
                                    List<PostsServiceTag> postsServiceTags) {
        List<ImgDto> imgDtos = postsImgConverter.toListDTO(posts.getPostsImgList());
        List<HashTagResponseDto> hashTags = hashTagConverter.toListDTO(postsHashTags);
        List<ServiceResponseDto> serviceTags = serviceTagConverter.toListDTO(postsServiceTags);
        List<RoomDto> roomDtos = roomConverter.toListDTO(roomList1);

        return PostsResponseDto.builder()
                .id(posts.getId())
                .title(posts.getTitle())
                .content(posts.getContent())
                .latitude(posts.getLatitude())
                .longitude(posts.getLongitude())
                .address(posts.getAddress())
                .detailAddress(posts.getDetailAddress())
                .phone(posts.getPhone())
                .companyId(companyId)
                .avgScore(posts.getAvgScore())
                .likesCount(posts.getLikesCount())
                .checkIn(posts.getCheckIn().format(DateTimeFormatter.ofPattern("a hh:mm").withLocale(Locale.KOREA)))
                .checkOut(posts.getCheckOut().format(DateTimeFormatter.ofPattern("a hh:mm").withLocale(Locale.KOREA)))
                .postsImgList(imgDtos)
                .hashTag(hashTags)
                .serviceTag(serviceTags)
                .roomDtos(roomDtos)
                .build();
    }


    public PostsResponseListDto combine(Posts post, Integer minPrice, ImgDto imgDto) {
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

    public PostsResponseDto combine(Posts posts, List<ReviewPostsResponse> reviewList, List<Room> roomList) {
        List<ImgDto> imgDtos = postsImgConverter.toListDTO(posts.getPostsImgList());
        List<HashTagResponseDto> hashTagResponseDtos = hashTagConverter.toListDTO(posts.getPostsHashTags());
        List<ServiceResponseDto> serviceResponseDtos = serviceTagConverter.toListDTO(posts.getPostAvailableTags());
        List<RoomDto> roomDtos = roomConverter.toListDTO(roomList);

        return PostsResponseDto.builder()
                .id(posts.getId())
                .title(posts.getTitle())
                .content(posts.getContent())
                .latitude(posts.getLatitude())
                .longitude(posts.getLongitude())
                .address(posts.getAddress())
                .detailAddress(posts.getDetailAddress())
                .phone(posts.getPhone())
                .companyId(posts.getCompanyId())
                .avgScore(posts.getAvgScore())
                .checkIn(posts.getCheckIn().format(DateTimeFormatter.ofPattern("a hh:mm")))
                .checkOut(posts.getCheckOut().format(DateTimeFormatter.ofPattern("a hh:mm")))
                .likesCount(posts.getLikesCount())
                .postsImgList(imgDtos)
                .hashTag(hashTagResponseDtos)
                .serviceTag(serviceResponseDtos)
                .roomDtos(roomDtos)
                .reviewList(reviewList)
                .build();
    }


}
