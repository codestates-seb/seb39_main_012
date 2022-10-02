package com.team012.server.review.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @Column(name = "score")
    private Double score;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "posts_id")
    private Long postsId;

    @OneToMany(mappedBy = "review", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ReviewImg> reviewImgList;
}
