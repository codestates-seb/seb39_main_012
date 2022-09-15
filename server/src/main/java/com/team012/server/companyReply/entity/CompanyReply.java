package com.team012.server.companyReply.entity;

import com.team012.server.company.entity.Company;
import com.team012.server.customer.entity.CustomerReview;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class CompanyReply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne
    @JoinColumn(name = "customer_review_id")
    private CustomerReview customerReview;
}


















