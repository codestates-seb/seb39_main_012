package com.team012.server.reservation.repository;

import com.team012.server.reservation.entity.ReservList;
import com.team012.server.reservation.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface ReservListRepository extends JpaRepository<ReservList, Long> {

    Page<ReservList> findByCompanyId(Long companyId, Pageable pageable);

    Optional<ReservList> findByUsersIdAndReservedId(Long usersId, Long reservedId);

    @Query(value = "select distinct r from ReservList r where r.usersId = :id and r.checkOut >= :date")
    Page<ReservList> findByUsersId(@Param("id") Long id, @Param("date") LocalDate date, Pageable pageable);

    @Query(value = "select r from ReservList r where r.usersId = :id and r.checkOut < :date")
    Page<ReservList> findByUsersIdAndWent(@Param("id") Long id, @Param("date") LocalDate date, Pageable pageable);
}
