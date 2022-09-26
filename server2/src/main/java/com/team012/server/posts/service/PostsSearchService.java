package com.team012.server.posts.service;

import com.team012.server.posts.Tag.HashTag.repository.PostsHashTagRepository;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.repository.PostsRepository;
import com.team012.server.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class PostsSearchService {

    private final PostsRepository postsRepository;

    //주소로 검색한 결과 페이지로 리턴
    public Page<Posts> findPostsByAddress(String address, int page, int size) {
        address = address.trim();
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "avgScore");
        Page<Posts> posts = postsRepository.findByAddressContaining(address, pageable);

        return posts;
    }

    //제목으로 검색한 결과 페이지로 리턴
    public Page<Posts> findPostsByTitle(String title, int page, int size) {
        title = title.trim();
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "avgScore");
        Page<Posts> posts =  postsRepository.findByTitleContaining(title, pageable);

        return posts;
    }
    //tag로 검색한 결과 페이지로 리턴
    public Page<Posts> findByTag(String tag, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "avgScore");
        Page<Posts> posts = postsRepository.findByHashTag(tag, pageable);

        return posts;
    }

//    public Page<Posts> findPostsByCheckInCheckOut(String strCheckIn, String strCheckOut, int page, int size) {
//        LocalDate checkIn = LocalDate.parse(strCheckIn, DateTimeFormatter.ISO_LOCAL_DATE);
//        LocalDate checkOut = LocalDate.parse(strCheckOut, DateTimeFormatter.ISO_LOCAL_DATE);
//    }


}
