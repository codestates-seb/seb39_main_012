package com.team012.server.posts.Tag.ServiceTag.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
public class ServiceTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tag")
    private String tag;

    @OneToMany(mappedBy = "serviceTag", cascade = CascadeType.REMOVE)
    private List<PostsServiceTag> postsServiceTagList = new ArrayList<>();

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Builder
    public ServiceTag(String tag) {
        this.tag = tag;
    }
}
