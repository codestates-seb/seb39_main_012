package com.team012.server.room.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size") // 대, 중, 소 (개 사이즈, 방 사이즈)
    private String roomSize;

    @Column(name = "price") // 두당 가격
    private Integer price;

    //갯수 엔티티 추가
    @Column(name = "ROOM_COUNT")
    private Integer roomCount;

    @Column(name = "POSTS_ID")
    private Long postsId;

}
