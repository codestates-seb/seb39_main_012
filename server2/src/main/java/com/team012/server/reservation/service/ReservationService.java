package com.team012.server.reservation.service;

import com.team012.server.reservation.dto.ReservationDto;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    // 회사별 예약 조회
    public List<Reservation> getReservation(Long id) {
        List<Reservation> reservation = reservationRepository.findByCompany_Id(id);
        // id 기준으로 내림차순 정렬
        reservation.sort(Comparator.reverseOrder());
        return reservation;
    }

    // 예약확인 --> 예약상태 수정
    public Reservation confirmReservation(ReservationDto.Patch dto) {
        Reservation reservation = reservationRepository.findById(dto.getId()).orElse(null);

        if (reservation != null) reservation = Reservation.builder().status("확정").build();

        throw new NullPointerException("데이터가 없습니다.");
    }
}
