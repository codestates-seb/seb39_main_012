package com.team012.server.posts.Tag.HashTag.entity;


import com.team012.server.posts.entity.Posts;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostsHashTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PostsTags_ID")
    private HashTag hashTag;

    @ManyToOne
    @JoinColumn(name = "Posts_ID")
    private Posts posts;

    @Builder
    public PostsHashTags(HashTag hashTag, Posts posts) {
        this.hashTag = hashTag;
        this.posts = posts;
    }

    public void setHashTag(HashTag hashTag) {
        this.hashTag = hashTag;
    }
}
