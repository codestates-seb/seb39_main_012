package com.team012.server.review.entity;

import com.team012.server.posts.entity.Posts;
import com.team012.server.users.entity.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "score")
    private Integer score;

    @Column(name = "review_img_url")
    private String reviewImgUrl;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

}
