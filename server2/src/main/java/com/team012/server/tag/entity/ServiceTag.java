package com.team012.server.tag.entity;

import com.team012.server.posts.entity.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class ServiceTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @ManyToOne
    @JoinColumn(name = "posts_id")
    private Posts posts;
}
