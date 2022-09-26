package com.team012.server.reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReservList {

    @PrePersist
    public void prePersist() {
        this.dogCount = this.dogCount == null? 0:this.dogCount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservedId;

    @Column(name = "check_in")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate checkIn;

    @Column(name = "check_out")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate checkOut;

    // 예약 확정인지 아닌지 판별 -->
    @Column(name = "status")
    private String status = "미정";

    @Column(name = "users_id")
    private Long usersId;

    @Column(name = "posts_id")
    private Long postsId;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "dog_count")
    private Integer dogCount; //예약된 강아지 수

    private String reservationCode;


    @ElementCollection(fetch = FetchType.LAZY)
    private List<Long> dogIdList = new ArrayList<>(); //강아지 카드 아이디 리스트

    @Embedded
    private UserInfo userInfo; //예약 상세 페이지에 이름, 전화번호, 이메일을 적는 칸이 있어서 넣었습니다.

    @OneToMany(mappedBy = "reservList")
    @JsonManagedReference
    private List<Reservation> reservations;

    public void setStatus(String status) {
        this.status = status;
    }

    @Builder
    public ReservList(LocalDate checkIn, LocalDate checkOut, String status, Long usersId, Long postsId, Integer totalPrice, Long companyId, Integer dogCount, String reservationCode, List<Long> dogIdList, UserInfo userInfo, List<Reservation> reservations) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
        this.usersId = usersId;
        this.postsId = postsId;
        this.totalPrice = totalPrice;
        this.companyId = companyId;
        this.dogCount = dogCount;
        this.reservationCode = reservationCode;
        this.dogIdList = dogIdList;
        this.userInfo = userInfo;
        this.reservations = reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
