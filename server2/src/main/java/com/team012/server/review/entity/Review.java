package com.team012.server.review.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team012.server.common.baseEntity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

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
