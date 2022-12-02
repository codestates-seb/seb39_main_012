package com.team012.server.reservation.entity;

import com.team012.server.common.utils.constant.Constant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {
    @PrePersist
    public void prePersist() {
        this.totalDogCount = this.totalDogCount == null? 0:this.totalDogCount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reservedId;

    @Column(name = "check_in")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate checkInDate;

    @Column(name = "check_out")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate checkOutDate;

    @Column(name = "check_in_time")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime checkInTime;

    @Column(name = "check_out_time")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime checkOutTime;

    // 예약 확정인지 아닌지 판별 -->
    @Column(name = "status")
    private String status = Constant.UNDEFINED.getMessage();

    @Column(name = "users_id")
    private Long usersId;

    @Column(name = "posts_id")
    private Long postsId;

    @Column(name = "total_price")
    private Integer totalPrice;

    @Column(name = "companyId")
    private Long companyId;

    @Column(name = "total_dog_count")
    private Integer totalDogCount; //예약된 강아지 수

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Long> dogIdList = new ArrayList<>(); //강아지 카드 아이디 리스트



    @Embedded
    private UserInfo userInfo; //예약 상세 페이지에 이름, 전화번호, 이메일을 적는 칸이 있어서 넣었습니다.

    @ElementCollection
    @CollectionTable
    @OrderColumn
    private List<ReservedRoomInfo> reservedRoomInfos = new ArrayList<>();

    public int calcTotalDogCount(List<ReservedRoomInfo> reservedRoomInfos) {
        return reservedRoomInfos.stream()
                .mapToInt(ReservedRoomInfo::getCount).sum();
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Builder
    public Reservation(Long reservedId,
                       LocalDate checkInDate,
                       LocalDate checkOutDate,
                       LocalTime checkInTime,
                       LocalTime checkOutTime,
                       String status,
                       Long usersId, Long postsId,
                       Integer totalPrice, Long companyId,
                       Integer totalDogCount, List<Long> dogIdList,
                       List<ReservedRoomInfo> reservedRoomInfos,
                       UserInfo userInfo) {
        this.reservedId = reservedId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.status = status;
        this.usersId = usersId;
        this.postsId = postsId;
        this.totalPrice = totalPrice;
        this.companyId = companyId;
        this.totalDogCount = totalDogCount;
        this.dogIdList = dogIdList;
        this.reservedRoomInfos = reservedRoomInfos;
        this.userInfo = userInfo;
    }

}
