package com.team012.server.posts.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import com.team012.server.posts.img.entity.PostsImg;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
// @DynamicUpdate --> 서버의 부하를 줄여준다 ?????
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    private String latitude;
    private String longitude;
    private String address;
    private String detailAddress;

    private Long companyId;

    private Integer likesCount;

    // 이미지 업로드 테이블

    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    @JsonManagedReference  // 순환참조 방지(...)
    private List<PostsImg> postsImgList = new ArrayList<>();


    @OneToMany(mappedBy = "posts", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private List<PostsServiceTag> postAvailableTags = new ArrayList<>();

    @OneToMany(mappedBy = "posts", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    private List<PostsHashTags> postsHashTags = new ArrayList<>();

    @Builder
    public Posts(String title, String content,
                 String latitude, String longitude,
                 String address, String detailAddress,
                 Long companyId) {
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.detailAddress = detailAddress;
        this.companyId = companyId;

    }
}
