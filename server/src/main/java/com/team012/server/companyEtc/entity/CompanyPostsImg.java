package com.team012.server.companyEtc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team012.server.companyPosts.entity.CompanyPosts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CompanyPostsImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "company_posts_id")
    @JsonIgnore
    private CompanyPosts companyPosts;

    @Builder
    public CompanyPostsImg(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
