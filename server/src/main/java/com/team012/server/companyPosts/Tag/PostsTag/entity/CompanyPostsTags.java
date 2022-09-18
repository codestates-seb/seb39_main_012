package com.team012.server.companyPosts.Tag.PostsTag.entity;


import com.team012.server.companyPosts.entity.CompanyPosts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CompanyPostsTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PostsTags_ID")
    private PostsTags postsTags;

    @ManyToOne
    @JoinColumn(name = "CompanyPosts_ID")
    private CompanyPosts companyPosts;

    @Builder
    public CompanyPostsTags(PostsTags postsTags, CompanyPosts companyPosts) {
        this.postsTags = postsTags;
        this.companyPosts = companyPosts;
    }
}
