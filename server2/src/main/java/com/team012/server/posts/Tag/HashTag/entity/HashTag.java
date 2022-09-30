package com.team012.server.posts.Tag.HashTag.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag")
    private String tag;

    @OneToMany(mappedBy = "hashTag", cascade = CascadeType.REMOVE)
    private List<PostsHashTags> postsHashTags = new ArrayList<>();

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Builder
    public HashTag(String tag) {
        this.tag = tag;
    }
}
