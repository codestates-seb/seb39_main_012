package com.team012.server.room.entity;

import com.team012.server.posts.entity.Posts;
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

    @Column(name = "size")
    private String size;

    // 방 갯수
    @Column(name = "count")
    private Integer count;

    @Column(name = "price")
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

}
