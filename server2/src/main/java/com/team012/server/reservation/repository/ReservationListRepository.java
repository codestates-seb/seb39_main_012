package com.team012.server.reservation.repository;

import com.team012.server.reservation.entity.ReservationList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationListRepository extends JpaRepository<ReservationList, Long> {

    Page<ReservationList> findByCompanyId(Long companyId, Pageable pageable);

    Optional<ReservationList> findByUsersIdAndReservedId(Long usersId, Long reservedId);

    @Query(value = "select distinct r from ReservationList r where r.usersId = :id and r.checkOutDate >= :date")
    Page<ReservationList> findByUsersIdBooked(@Param("id") Long id, @Param("date") LocalDate date, Pageable pageable);

    @Query(value = "select r from ReservationList r where r.usersId = :id and r.checkOutDate < :date")
    Page<ReservationList> findByUsersIdVisited(@Param("id") Long id, @Param("date") LocalDate date, Pageable pageable);

//    @Query("select * from ReservationList r where r.checkIn < :checkOut and r.checkOut > :checkIn")
//    List<ReservationList> findByCheckInCheckOut(LocalDate checkIn, LocalDate checkOut);
}
