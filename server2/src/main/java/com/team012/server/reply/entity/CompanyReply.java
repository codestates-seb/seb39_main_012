package com.team012.server.reply.entity;

import com.team012.server.users.entity.Users;
import com.team012.server.review.entity.Review;
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
    private Users company;

    @OneToOne
    @JoinColumn(name = "customer_review_id")
    private Review review;
}


















