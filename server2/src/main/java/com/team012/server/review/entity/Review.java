package com.team012.server.review.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "posts_id")
    private Long postsId;

    @OneToMany(mappedBy = "review")
    @JsonIgnore
    private List<ReviewImg> reviewImgList;

    @Builder
    public Review(String content, Integer score, Long postsId, Long userId) {
        this.postsId = postsId;
        this.userId = userId;
        this.content = content;
        this.score = score;
    }
}
