package com.team012.server.companyPosts.Tag.AvaliableServiceTags.entity;


import com.team012.server.companyPosts.entity.CompanyPosts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class PostAvailableTags {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "availableServiceTags_id")
    private AvailableServiceTags availableServiceTags;

    @ManyToOne
    @JoinColumn(name = "CompanyPosts_id")
    private CompanyPosts companyPosts;

    @Builder
    public PostAvailableTags(AvailableServiceTags availableServiceTags, CompanyPosts companyPosts) {
        this.availableServiceTags = availableServiceTags;
        this.companyPosts = companyPosts;
    }
}
