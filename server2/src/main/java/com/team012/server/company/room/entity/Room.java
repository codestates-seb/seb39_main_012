package com.team012.server.company.room.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team012.server.company.entity.Company;
import com.team012.server.usersPack.users.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "size") // 대, 중, 소 (개 사이즈, 방 사이즈)
    private String size;

    @Column(name = "price") // 두당 가격
    private Integer price;

    @ManyToOne
    @JoinColumn(name = "users_id")
    @JsonIgnore
    private Users users;

    @Column(name = "posts_id")
    private Long postsId;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private Company company;

    public void setCompany(Company company) {
        this.company = company;
        if(!company.getRoom().contains(this)) {
            company.getRoom().add(this);
        }
    }

    @Builder
    public Room(String size,Integer price, Long postsId) {
        this.size = size;
        this.price = price;
        this.postsId = postsId;
    }

}
