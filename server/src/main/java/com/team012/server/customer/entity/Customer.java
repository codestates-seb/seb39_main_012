package com.team012.server.customer.entity;

import com.team012.server.like.entity.Like;
import com.team012.server.reservation.entity.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;

    // API 접근 권한 때문에..
    @Column(name = "role")
    private String role;

    // 고객은 여러개의 좋아요를 할 수 있다.
    @OneToMany(mappedBy = "customer")
    private List<Like> likeList;

    // 고객은 여러가지 강아지 큐카드를 가질 수 있다.
    @OneToMany(mappedBy = "customer", cascade = CascadeType.REMOVE)
    private List<DogCard> dogCardList;

    // 고객은 여러개의 리뷰를 작성 할 수 있다.
    @OneToMany(mappedBy = "customer")
    private List<CustomerReview> customerReviewList;

    @OneToMany(mappedBy = "customer")
    private List<Reservation> reservationList;

    @Builder
    public Customer(String email, String password, String username, String role) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.role = role;
    }
}
