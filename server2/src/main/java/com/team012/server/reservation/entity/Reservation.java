package com.team012.server.reservation.entity;

import com.team012.server.users.entity.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

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
    private String totalPrice;

    // 예약 확정인지 아닌지 판별 -->
    @Column(name = "status")
    private String status = "미정";

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @Builder
    public Reservation(LocalDate checkIn, LocalDate checkOut,
                       String totalPrice, String status) {
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalPrice = totalPrice;
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
