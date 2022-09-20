package com.team012.server.room.entity;

import com.team012.server.users.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size") // 대, 중, 소 (개 사이즈, 방 사이즈)
    private String size;

    // 방 갯수
    @Column(name = "count") // 갯수, 마리
    private Integer count;

    @Column(name = "price") // 두당 가격
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

}
