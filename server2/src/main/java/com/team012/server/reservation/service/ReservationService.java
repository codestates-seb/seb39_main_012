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

    // 예약하기 API  --> 고객 / 사장님이 예약조회를 할 때... 가격도 볼 수 있게  // 예약 확인창..!
//    public Reservation bookReservation(ReservationDto.Post dto) {
//        LocalDate checkIn = LocalDate.parse(dto.getCheckIn(), DateTimeFormatter.ISO_DATE);
//        LocalDate checkOut = LocalDate.parse(dto.getCheckOut(), DateTimeFormatter.ISO_DATE);
//
//        // 가격은 방 선택 or 강아지 종에 따라 달라지기 때문에 강아지 정보나 방정보 선택 정보를 받아야 한다.
//        Reservation reservation
//                = Reservation
//                .builder()
//                .companyId(dto.getCompanyId())
//                .checkIn(checkIn)
//                .checkOut(checkOut)
//                .build();
//
//        return reservationRepository.save(reservation);
//    }

    // 예약수정 API  --> 사장님 용도인데 고객이랑 쇼부치고 난 이후....
//    public Reservation editReservation(ReservationDto.Patch dto) {
//        Reservation reservation = reservationRepository.findById(dto.getId()).orElse(null);
//        if (reservation == null) throw new NullPointerException();
//        reservation = Reservation
//                .builder()
//                .build();
//
//        return null;
//    }

    // 예약취소 API
    public void cancelReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
