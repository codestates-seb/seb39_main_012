package com.team012.server.posts.entity;

import com.team012.server.img.entity.PostsImg;
import com.team012.server.tag.entity.ServiceTag;
import com.team012.server.users.entity.Users;
import com.team012.server.like.entity.Like;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "address")
    private String address;

    @OneToOne
    @JoinColumn(name = "users_id")
    private Users users;

    // 이미지 업로드 테이블
    @OneToMany(mappedBy = "posts")
    private List<PostsImg> postsImgList;

    // 좋아요
    @OneToMany(mappedBy = "posts")
    private List<Like> likeList;

    // 서비스 태그
    @OneToMany(mappedBy = "posts")
    List<ServiceTag> serviceTagList;

    // 해쉬 태그

}
