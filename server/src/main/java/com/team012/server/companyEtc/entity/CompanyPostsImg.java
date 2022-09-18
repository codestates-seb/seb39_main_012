package com.team012.server.companyEtc.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.team012.server.companyPosts.entity.CompanyPosts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CompanyPostsImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "company_posts_id")
    @JsonBackReference
    private CompanyPosts companyPosts;

    @Builder
    public CompanyPostsImg(String fileName, String imgUrl) {
        this.fileName = fileName;
        this.imgUrl = imgUrl;
    }
}
