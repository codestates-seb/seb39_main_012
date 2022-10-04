package com.team012.server.posts.img.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team012.server.posts.entity.Posts;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostsImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "img_url")
    @Lob
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "posts_id")
    @JsonBackReference
    private Posts posts;

    @Builder
    public PostsImg(String fileName, String imgUrl) {
        this.fileName = fileName;
        this.imgUrl = imgUrl;
    }
}
