package com.team012.server.posts.service;

import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.repository.PostsRepository;
import com.team012.server.posts.repository.PostsRepositoryImpl;
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
    private final PostsRepositoryImpl postsRepositoryImpl;


    public Page<Posts> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "avgScore");
        Page<Posts> posts = postsRepository.findAll(pageable);

        return posts;
    }

    //주소로 검색한 결과 페이지로 리턴
    public Page<Posts> findPostsByAddress(String address, int page, int size) {
        address = address.trim();
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "avgScore");
        Page<Posts> posts = postsRepositoryImpl.findByAddressContaining(address, pageable);

        return posts;
    }

    //제목으로 검색한 결과 페이지로 리턴
    public Page<Posts> findPostsByTitleOrContents(String title, String contents, int page, int size) {
        title = title.trim();
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "avgScore");
        Page<Posts> posts =  postsRepositoryImpl.searchPageByTitleAndContents(title, contents, pageable);

        return posts;
    }
    public Page<RoomPriceDto> findAllRoomPrice(int page, int size) {
        return postsRepository.findAllRoomMinPrice(PageRequest.of(page, size, Sort.Direction.DESC, "avgScore"));
    }

    public Page<Posts> findByHashTag(String tag, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "avgScore");
        Page<Posts> posts = postsRepositoryImpl.findByHashTags(tag, pageable);

        return posts;
    }
    public Page<RoomPriceDto> findAllRoomPrice(int page, int size, String tags) {
        return postsRepositoryImpl.findAllRoomMinPriceByTags(tags,PageRequest.of(page, size, Sort.Direction.DESC, "avgScore"));
    }

    public Page<RoomPriceDto> findAllRoomPriceAddress(int page, int size, String address) {
        return postsRepositoryImpl.findAllRoomMinPriceAddressContain(PageRequest.of(page, size, Sort.Direction.DESC, "avgScore"), address);
    }

    public Page<RoomPriceDto> findAllRoomPriceByTitleOrContents(int page, int size, String title, String contents) {
        return postsRepositoryImpl.findAllRoomMinPriceTitleOrContentsContaining(title, contents ,PageRequest.of(page, size, Sort.Direction.DESC, "avgScore"));
    }


}
