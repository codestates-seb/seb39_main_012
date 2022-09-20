package com.team012.server.review.entity;

import com.team012.server.img.entity.ReviewImg;
import com.team012.server.posts.entity.Posts;
import com.team012.server.users.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @OneToMany(mappedBy = "review")
    private List<ReviewImg> reviewImgList;

    @Builder
    public Review(String content, Integer score) {
        this.content = content;
        this.score = score;
    }
}
