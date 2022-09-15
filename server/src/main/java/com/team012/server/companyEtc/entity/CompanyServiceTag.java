package com.team012.server.companyEtc.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
public class CompanyServiceTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;


//    @ManyToOne
//    @JoinColumn(name = "company_posts_id")
//    private CompanyPosts companyPosts;
}
