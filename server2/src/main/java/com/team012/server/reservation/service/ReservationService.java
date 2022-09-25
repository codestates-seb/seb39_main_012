package com.team012.server.reservation.service;

import com.team012.server.reservation.repository.ReservationRepository;
import com.team012.server.reservation.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // 회사별 예약 조회
    public Page<Reservation> getReservation(Long companyId, Integer page, Integer size) {

        // id 기준으로 내림차순 정렬
        Page<Reservation> reservation = reservationRepository.findByCompanyId(companyId,
                PageRequest.of(page, size, Sort.by("usersId").descending()));

        return reservation;
    }

    // 예약확인 --> 예약상태 수정
    public void confirmReservation(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("reservation Not found"));

        if (reservation != null) {
            reservation.setStatus("확정");
            reservationRepository.save(reservation);
        }

    }

    // 예약취소 API
    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
