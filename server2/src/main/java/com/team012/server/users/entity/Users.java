package com.team012.server.users.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.team012.server.users.entity.DogCard;
import com.team012.server.utils.baseEntity.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;

    // 역할 구분 API 접근 권한 때문에
    @Column(name = "roles")
    private String roles;

    // 강아지 카드
    @OneToMany(mappedBy = "users")
    @JsonManagedReference
    private List<DogCard> dogCardList;

    @Builder
    public Users(String email, String password,
                 String username, String phone,
                 String roles) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.phone = phone;
        this.roles = roles;
    }

    public List<String> getRoleList() {
        if (this.roles.length() > 0) {
            return Arrays.asList(this.roles.split(","));
        }
        return new ArrayList<>();
    }
}
