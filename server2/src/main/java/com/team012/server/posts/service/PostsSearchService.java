package com.team012.server.posts.service;

import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.repository.PostsRepository;
import com.team012.server.posts.repository.RoomPriceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class PostsSearchService {

    private final PostsRepository postsRepository;


    public Page<Posts> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "avgScore");
        Page<Posts> posts = postsRepository.findAll(pageable);

        return posts;
    }

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
    public Page<RoomPriceDto> findAllRoomPrice(int page, int size) {
        return postsRepository.findAllRoomMinPrice(PageRequest.of(page, size, Sort.Direction.DESC, "avgScore"));
    }

    public Page<RoomPriceDto> findAllRoomPriceAddress(int page, int size, String address) {
        return postsRepository.findAllRoomMinPriceAddressContaining(PageRequest.of(page, size, Sort.Direction.DESC, "avgScore"), address);
    }

    public Page<RoomPriceDto> findAllRoomPriceTitle(int page, int size, String title) {
        return postsRepository.findAllRoomMinPriceTitleContaining(PageRequest.of(page, size, Sort.Direction.DESC, "avgScore"), title);
    }


}
