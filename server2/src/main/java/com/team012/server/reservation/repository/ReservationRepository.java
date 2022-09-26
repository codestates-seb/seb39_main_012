package com.team012.server.reservation.repository;

import com.team012.server.reservation.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    @Query("select COALESCE(sum(r.dogCount),0) from Reservation r " +
            "where r.companyId = :companyId group by r.reservationDate " +
            "having r.reservationDate between :checkIn and :checkOut")
    List<Integer> findByCheckInCheckOut(@Param("checkIn") LocalDate checkIn
            , @Param("checkOut") LocalDate checkOut
            , @Param("companyId")Long companyId);

}
