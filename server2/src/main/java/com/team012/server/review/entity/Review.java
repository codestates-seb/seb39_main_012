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

    @Column(name = "TITLE")
    private String title;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "SCORE")
    private Double score;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "POSTS_ID")
    private Long postsId;

    @OneToMany(mappedBy = "REVIEW", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<ReviewImg> reviewImgList;
}
