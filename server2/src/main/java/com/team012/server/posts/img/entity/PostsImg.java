package com.team012.server.posts.img.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team012.server.posts.entity.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PostsImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Column(name = "img_url")
    @Lob
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    @JsonBackReference
    private Posts posts;

    @Builder
    public PostsImg(String fileName, String imgUrl) {
        this.fileName = fileName;
        this.imgUrl = imgUrl;
    }
}
