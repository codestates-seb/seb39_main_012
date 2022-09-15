package com.team012.server.like.entity;

import com.team012.server.companyPosts.entity.CompanyPosts;
import com.team012.server.customer.entity.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "company_posts_id")
    private CompanyPosts companyPosts;
}
