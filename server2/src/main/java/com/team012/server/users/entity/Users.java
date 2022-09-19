package com.team012.server.users.entity;

import com.team012.server.baseEntity.BaseEntity;
import com.team012.server.dogCard.entity.DogCard;
import com.team012.server.posts.entity.Posts;
import com.team012.server.reply.entity.CompanyReply;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.review.entity.Review;
import com.team012.server.room.entity.Room;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "address")
    private String address;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;

    // 역할 구분 API 접근 권한 때문에
    @Column(name = "role")
    private String role;

    // 리뷰에 사장님이 댓글 다는 기능인데 지금은 보류
    @OneToMany(mappedBy = "company")
    private List<CompanyReply> companyReplyList;

    // 예약
    @OneToMany(mappedBy = "users")
    private List<Reservation> reservationList;

    // 게시글
    @OneToOne(mappedBy = "users")
    private Posts posts;

    // 방
    @OneToMany(mappedBy = "users")
    private List<Room> roomList;

    // 리뷰
    @OneToMany(mappedBy = "users")
    private List<Review> reviewList;

    // 강아지 카드
    @OneToMany(mappedBy = "users")
    private List<DogCard> dogCardList;

    @Builder
    public Users(String email, String password,
                 String companyName, String address,
                 String username, String phone,
                 String role) {
        this.email = email;
        this.password = password;
        this.companyName = companyName;
        this.address = address;
        this.username = username;
        this.phone = phone;
        this.role = role;
    }
}
