package com.team012.server.customer.entity;

import com.team012.server.companyPosts.entity.CompanyPosts;
import com.team012.server.companyReply.entity.CompanyReply;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
public class CustomerReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "company_posts_id")
    private CompanyPosts companyPosts;

    @OneToOne(mappedBy = "customerReview")
    private CompanyReply companyReply;

}
