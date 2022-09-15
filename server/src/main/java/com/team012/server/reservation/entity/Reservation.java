package com.team012.server.reservation.entity;

import com.team012.server.company.entity.Company;
import com.team012.server.customer.entity.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "check_in") // 20
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate checkIn;

    @Column(name = "check_out")
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate checkOut;

    @Column(name = "price")
    private String price;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Company company;
}
