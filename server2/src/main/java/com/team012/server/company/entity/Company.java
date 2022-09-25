package com.team012.server.company.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "CEO")
    private String ceo;

    @Column(name = "address")
    private String address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "user_id")
    private Long userId;

    @Builder
    public Company(String companyName, String ceo, String address, String detailAddress, Long userId) {
        this.companyName = companyName;
        this.ceo = ceo;
        this.address = address;
        this.detailAddress = detailAddress;
        this.userId = userId;
    }
}
