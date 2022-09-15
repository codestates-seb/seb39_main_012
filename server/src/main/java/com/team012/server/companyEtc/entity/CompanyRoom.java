package com.team012.server.companyEtc.entity;

import com.team012.server.companyPosts.entity.CompanyPosts;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CompanyRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "big")
    private Integer big;

    @Column(name = "medium")
    private Integer medium;

    @Column(name = "small")
    private Integer small;

    @OneToOne(mappedBy = "companyRoom")
    private CompanyPosts companyPosts;
}
