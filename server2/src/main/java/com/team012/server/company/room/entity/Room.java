package com.team012.server.company.room.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team012.server.usersPack.users.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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
    @JsonIgnore
    private Users users;

    @Column(name = "posts_id")
    private Long postsId;

    @Builder
    public Room(String size, Integer count, Integer price, Long postsId) {
        this.size = size;
        this.count = count;
        this.price = price;
        this.postsId = postsId;
    }

}
