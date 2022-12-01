package com.team012.server.posts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import com.team012.server.posts.img.entity.PostsImg;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Posts {

    @PrePersist
    public void prePersist() {
        this.likesCount = this.likesCount == null? 0:this.likesCount;
        this.avgScore = this.avgScore == null? 0 : this.avgScore;
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

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "company_id")
    private Long companyId;

    @Column(name = "likes_count")
    private Integer likesCount;

    @Column(name = "avg_score")
    private Double avgScore;

    @Column(name = "check_in")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime checkInTime;

    @Column(name = "check_out")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime checkOutTime;

    // 이미지 업로드 테이블
    @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
    @JsonManagedReference  // 순환참조 방지(...)
    private List<PostsImg> postsImgList = new ArrayList<>();

    @OneToMany(mappedBy = "posts", cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private List<PostsServiceTag> postAvailableTags = new ArrayList<>();

    @OneToMany(mappedBy = "posts", cascade = {CascadeType.REMOVE})
    @JsonIgnore
    private List<PostsHashTags> postsHashTags = new ArrayList<>();

    @Builder
    public Posts(String title, String content,
                 String latitude, String longitude,
                 String address, String detailAddress, String phone,
                 Long companyId, LocalTime checkInTime, LocalTime checkOutTime) {
        this.title = title;
        this.content = content;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.detailAddress = detailAddress;
        this.phone = phone;
        this.companyId = companyId;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;

    }
}
