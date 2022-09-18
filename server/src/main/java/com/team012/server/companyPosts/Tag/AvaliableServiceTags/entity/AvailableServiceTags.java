package com.team012.server.companyPosts.Tag.AvaliableServiceTags.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
@NoArgsConstructor
public class AvailableServiceTags {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tag;

    @Builder
    public AvailableServiceTags(String tag) {
        this.tag = tag;
    }
}
