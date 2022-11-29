package com.team012.server.posts.repository;


import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.team012.server.posts.entity.Posts;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.team012.server.posts.entity.QPosts.posts;
import static com.team012.server.room.entity.QRoom.room;

@Repository
@RequiredArgsConstructor
public class PostsRepositoryImpl implements  PostsRepositoryCustom{

    private final JPAQueryFactory queryFactory;


    @Override
    public Page<Posts> searchPageByTitleAndContents(String title, String contents, Pageable pageable) {
        List<Posts> postsList = queryFactory
                .select(posts)
                .from(posts)
                .where(isTitleContains(title), isContentsContains(contents))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPQLQuery<Posts> count = queryFactory
                .selectFrom(posts)
                .where(isTitleContains(title), isContentsContains(contents));

        return PageableExecutionUtils.getPage(postsList,pageable, count::fetchCount);

    }

    @Override
    public Page<RoomPriceDto> findAllRoomMinPriceTitleOrContentsContaining(String title, String contents, Pageable pageable) {
        List<RoomPriceDto> postsList = queryFactory
                .select(Projections.constructor(RoomPriceDto.class, room.postsId, room.price.min()))
                .from(posts, room)
                .where(posts.id.eq(room.postsId), isTitleContains(title), isContentsContains(contents))
                .groupBy(posts.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPQLQuery<RoomPriceDto> count = queryFactory
                .select(Projections.constructor(RoomPriceDto.class, room.postsId, room.price.min()))
                .from(posts, room)
                .where(posts.id.eq(room.postsId), isTitleContains(title), isContentsContains(contents))
                .groupBy(posts.id);

        return PageableExecutionUtils.getPage(postsList,pageable, count::fetchCount);
    }

    @Override
    public Page<Posts> findByAddressContaining(String address, Pageable pageable) {
        List<Posts> postsList = queryFactory
                .select(posts)
                .from(posts)
                .where(posts.address.contains(address))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPQLQuery<Posts> count = queryFactory
                .selectFrom(posts)
                .where(posts.address.contains(address));

        return PageableExecutionUtils.getPage(postsList,pageable, count::fetchCount);
    }

    @Override
    public Page<RoomPriceDto> findAllRoomMinPriceAddressContaining(Pageable pageable, String address) {
        List<RoomPriceDto> postsList = queryFactory
                .select(Projections.constructor(RoomPriceDto.class, room.postsId, room.price.min()))
                .from(posts, room)
                .where(posts.id.eq(room.postsId), isAddressContains(address))
                .groupBy(posts.id)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPQLQuery<RoomPriceDto> count = queryFactory
                .select(Projections.constructor(RoomPriceDto.class, room.postsId, room.price.min()))
                .from(posts, room)
                .where(posts.id.eq(room.postsId), isAddressContains(address))
                .groupBy(posts.id);

        return PageableExecutionUtils.getPage(postsList,pageable, count::fetchCount);
    }

    private BooleanExpression isTitleContains(String title) {
        return title != null ? posts.title.contains(title) : null;
    }

    private BooleanExpression isContentsContains(String contents) {
        return contents != null? posts.content.contains(contents) : null;
    }

    private BooleanExpression isAddressContains(String address) {
        return address != null? posts.address.contains(address) : null;
    }

}
