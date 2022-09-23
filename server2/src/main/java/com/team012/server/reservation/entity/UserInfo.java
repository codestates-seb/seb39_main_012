package com.team012.server.reservation.entity;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class UserInfo {

    private String name;
    private String phone;
    private String email;
}
