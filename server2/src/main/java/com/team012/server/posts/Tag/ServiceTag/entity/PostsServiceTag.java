package com.team012.server.posts.Tag.ServiceTag.entity;


import com.team012.server.posts.entity.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PostsServiceTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "serviceTags_id")
    private ServiceTag serviceTag;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "posts_id")
    private Posts posts;

    @Builder
    public PostsServiceTag(ServiceTag serviceTag, Posts posts) {
        this.serviceTag = serviceTag;
        this.posts = posts;
    }
}
