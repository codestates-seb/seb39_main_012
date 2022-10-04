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

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;

    public void setServiceTag(ServiceTag serviceTag) {
        this.serviceTag = serviceTag;
    }

    @Builder
    public PostsServiceTag(ServiceTag serviceTag, Posts posts) {
        this.serviceTag = serviceTag;
        this.posts = posts;
    }
}
