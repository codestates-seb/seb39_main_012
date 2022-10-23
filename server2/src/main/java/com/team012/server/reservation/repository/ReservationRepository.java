package com.team012.server.reservation.repository;

import com.team012.server.reservation.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Reservation findByCompanyId(Long companyId);

    Page<Reservation> findByCompanyId(Long companyId, Pageable pageable);

    Optional<Reservation> findByUsersIdAndReservedId(Long usersId, Long reservedId);

    @Query(value = "select " +
            " distinct r from Reservation " +
            " r where r.usersId = :id and r.checkOutDate >= :date")
    Page<Reservation> findByUsersIdBooked(@Param("id") Long id, @Param("date") LocalDate date, Pageable pageable);

    @Query(value = "select " +
            " r from Reservation r " +
            " where r.usersId = :id and r.checkOutDate < :date")
    Page<Reservation> findByUsersIdVisited(@Param("id") Long id, @Param("date") LocalDate date, Pageable pageable);

    @Query("select " +
            " r from Reservation r " +
            " where r.companyId = :companyId")
    List<Reservation> findByCheckInCheckOut(Long companyId);
}
