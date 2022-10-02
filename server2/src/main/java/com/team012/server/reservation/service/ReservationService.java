package com.team012.server.reservation.service;

import com.team012.server.reservation.entity.ReservationList;
import com.team012.server.reservation.repository.ReservationListRepository;
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
    private final ReservationListRepository reservationListRepository;

    // 회사별 예약 조회
    @Transactional(readOnly = true)
    public Page<ReservationList> getReservation(Long companyId, Integer page, Integer size) {
        // id 기준으로 내림차순 정렬
        Page<ReservationList> reservation = reservationListRepository.findByCompanyId(companyId,
                PageRequest.of(page, size, Sort.by("usersId").descending()));

        return reservation;
    }

    // 예약확인 --> 예약상태 수정
    public void confirmReservation(Long reservationId) {
        ReservationList reservation = reservationListRepository.findById(reservationId).orElseThrow(() -> new RuntimeException("reservation Not found"));

        if (reservation != null) {
            reservation.setStatus("확정");
            reservationListRepository.save(reservation);
        }

    }

    // 예약취소 API
    public void cancelReservation(Long id) {
        ReservationList reservationList = reservationListRepository.findById(id).orElseThrow(NoSuchElementException::new);
        List<Reservation> reservations = reservationList.getReservations();

        reservationRepository.deleteAll(reservations);
        reservationListRepository.deleteById(id);
    }
}
