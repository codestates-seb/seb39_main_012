package com.team012.server.reservation.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
public class Reservation implements Comparable<Reservation> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in") // 20
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate checkIn;

    @Column(name = "check_out")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate checkOut;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "companyId")
    private Long companyId;

    // 예약 확정인지 아닌지 판별 -->
    @Column(name = "status")
    private String status = "미정";

    private Long usersId;

    private Long postsId;

    @Column(columnDefinition = "integer default 0")
    private Integer dogCount; //예약된 강아지 수

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Long> dogIdList = new ArrayList<>(); //강아지 카드 아이디 리스트

    @Embedded
    private UserInfo userInfo; //예약 상세 페이지에 이름, 전화번호, 이메일을 적는 칸이 있어서 넣었습니다.

    @Builder
    public Reservation(LocalDate checkIn, LocalDate checkOut, Integer totalPrice, Long companyId, String status, Long usersId, Long postsId, Integer dogCount, List<Long> dogIdList, UserInfo userInfo) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
        this.companyId = companyId;
        this.status = status;
        this.usersId = usersId;
        this.postsId = postsId;
        this.dogCount = dogCount;
        this.dogIdList = dogIdList;
        this.userInfo = userInfo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // id 기준으로 정렬
    @Override
    public int compareTo(Reservation reservation) {
        if (this.id < reservation.id) {
            return -1;
        } else if (this.id.equals(reservation.id)) {
            return 0;
        } else {
            return 1;
        }
    }
}


