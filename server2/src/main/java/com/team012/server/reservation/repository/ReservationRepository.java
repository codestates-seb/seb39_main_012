package com.team012.server.reservation.repository;

import com.team012.server.reservation.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Page<Reservation> findByUsers_Id(Long id, PageRequest pageRequest);
    List<Reservation> findByUsers_Id(Long id);

    Reservation findByIdAndUsers_Id(Long reservationId, Long usersId);
}
