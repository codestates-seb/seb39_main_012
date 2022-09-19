package com.team012.server.reservation.repository;

import com.team012.server.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByCompany_Id(Long id);
}
