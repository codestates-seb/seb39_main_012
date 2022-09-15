package com.team012.server.company.entity;

import com.team012.server.companyPosts.entity.CompanyPosts;
import com.team012.server.companyReply.entity.CompanyReply;
import com.team012.server.reservation.entity.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "CEO")
    private String ceo;

    @Column(name = "address")
    private String address;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;

    // 역할 구분 API 접근 권한 때문에
    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "company")
    private List<CompanyReply> companyReplyList;

    @OneToMany(mappedBy = "company")
    private List<Reservation> reservationList;

    @OneToOne(mappedBy = "company")
    private CompanyPosts companyPosts;

    @Builder
    public Company(String email, String password,
                   String companyName, String ceo,
                   String address, String username,
                   String role) {
        this.email = email;
        this.password = password;
        this.companyName = companyName;
        this.ceo = ceo;
        this.address = address;
        this.username = username;
        this.role = role;
    }

}
