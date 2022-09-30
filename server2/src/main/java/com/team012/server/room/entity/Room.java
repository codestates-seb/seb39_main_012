package com.team012.server.room.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size") // 대, 중, 소 (개 사이즈, 방 사이즈)
    private String size;

    @Column(name = "price") // 두당 가격
    private Integer price;

    @Column(name = "posts_id")
    private Long postsId;

}
