package com.team012.server.reservation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @PrePersist
    public void prePersist() {
        this.dogCount = this.dogCount == null ? 0 : this.dogCount;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long companyId;

    @Column(name = "date") // 20
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate reservationDate;

    //checkIn : 11/1 ~ checkOut : 11/05
    /*
    * reservation1 11/1
    * reservation2 11/2
    * reservation3 11/3
    * reservation4 11/4
    *
    *
    * */

    private Integer dogCount;

    @ManyToOne
    @JoinColumn(name = "reservList_id")
    @JsonBackReference
    private ReservList reservList;

    public void setReservList(ReservList reservList) {
        this.reservList = reservList;
        if(!reservList.getReservations().contains(this)) {
            reservList.getReservations().add(this);
        }
    }

    @Builder
    public Reservation(LocalDate reservationDate, Long companyId, Integer dogCount) {
    
        this.reservationDate = reservationDate;
        this.companyId = companyId;
        this.dogCount = dogCount;
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


