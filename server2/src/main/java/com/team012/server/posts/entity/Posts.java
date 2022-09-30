package com.team012.server.posts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import com.team012.server.posts.img.entity.PostsImg;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
// @DynamicUpdate --> 서버의 부하를 줄여준다 ?????
public class Posts {

    @PrePersist
    public void prePersist() {
        this.likesCount = this.likesCount == null? 0:this.likesCount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    @Lob
    private String content;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "address")
    private String address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "room_count")
    private Integer roomCount; //room에 count없에는 대신 추가(전체 방의 갯수)

    @Column(name = "likes_count")
    private Integer likesCount;

    @Column(name = "avg_score")
    private Double avgScore;

    @Column(name = "check_in")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime checkIn;

    @Column(name = "check_out")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime checkOut;

    // 이미지 업로드 테이블
    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    @JsonManagedReference  // 순환참조 방지(...)
    private List<PostsImg> postsImgList = new ArrayList<>();

    @OneToMany(mappedBy = "posts", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JsonIgnore
    private List<PostsServiceTag> postAvailableTags = new ArrayList<>();

    @OneToMany(mappedBy = "posts", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
    @JsonIgnore
    private List<PostsHashTags> postsHashTags = new ArrayList<>();

    @Builder
    public Posts(String title, String content,
                 String latitude, String longitude,
                 String address, String detailAddress, Integer roomCount,
                 Long companyId, Double avgScore,Integer likesCount, LocalTime checkIn, LocalTime checkOut) {
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.detailAddress = detailAddress;
        this.roomCount = roomCount; // add
        this.companyId = companyId;
        this.avgScore = avgScore; // add
        this.likesCount = likesCount;
        this.checkIn = checkIn;
        this.checkOut = checkOut;

    }
}
