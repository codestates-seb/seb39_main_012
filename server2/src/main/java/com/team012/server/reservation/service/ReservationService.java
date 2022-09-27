package com.team012.server.reservation.service;

import com.team012.server.reservation.entity.ReservList;
import com.team012.server.reservation.repository.ReservListRepository;
import com.team012.server.reservation.repository.ReservationRepository;
import com.team012.server.reservation.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;


@Transactional
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ReservListRepository reservListRepository;

    // 게시글에 예약된 목록 단순 조회
    public List<Reservation> getReservationList(Long postsId) {
        return reservationRepository.findByPostsId(postsId);
    }

    // 회사별 예약 조회
    @Transactional(readOnly = true)
    public Page<ReservList> getReservation(Long companyId, Integer page, Integer size) {
        // id 기준으로 내림차순 정렬
        Page<ReservList> reservation = reservListRepository.findByCompanyId(companyId,
                PageRequest.of(page, size, Sort.by("usersId").descending()));

        return reservation;
    }

    // 예약확인 --> 예약상태 수정
    public void confirmReservation(Long reservationId) {
        ReservList reservation = reservListRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("reservation Not found"));

        if (reservation != null) {
            reservation.setStatus("확정");
            reservListRepository.save(reservation);
        }

    }

    // 예약취소 API
    public void cancelReservation(Long id) {
        ReservList reservList = reservListRepository.findById(id).orElseThrow(NoSuchElementException::new);
        List<Reservation> reservations = reservList.getReservations();

        reservationRepository.deleteAll(reservations);
        reservListRepository.deleteById(id);
    }
}
