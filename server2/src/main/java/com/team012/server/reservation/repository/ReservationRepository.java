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

    Page<Reservation> findByCompanyId(Long id, PageRequest pageRequest);

    //미래에 갈 예약 내역
    @Query(value = "select r from Reservation r where r.usersId = :id and r.checkOut >= :date")
    Page<Reservation> findByUsersId(@Param("id") Long id, @Param("date") LocalDate date, Pageable pageable);

    //과거 내역
    @Query(value = "select r from Reservation r where r.usersId = :id and r.checkOut < :date")
    Page<Reservation> findByUsersIdAndWent(@Param("id") Long id, @Param("date") LocalDate date, Pageable pageable);

    //    지정한 체크인과 체크아웃 사이에 호텔의 예약이 몇개 되어있는지 확인
    @Query("select COALESCE(sum(r.dogCount),0) from Reservation r where r.companyId = :companyId and " +
            "((:checkIn < r.checkIn and r.checkIn < :checkOut) or " +
            "(:checkIn between r.checkIn and r.checkOut) or " +
            "(:checkIn < r.checkIn and r.checkIn < :checkOut))")
    //COALESCE  -> 값이 없을 경우 두번째 파라미터를 리턴한다고 합니다...
    Integer findByCheckInCheckOut(LocalDate checkIn, LocalDate checkOut, Long companyId);

    // 게시글 아이디로 예약목록 조회
    List<Reservation> findByPostsId(Long postsId);

}
