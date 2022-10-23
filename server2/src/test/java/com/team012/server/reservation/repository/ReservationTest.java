//package com.team012.server.reservation.repository;
//
//import com.team012.server.reservation.entity.Reservation;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//public class ReservationTest {
//
//    @Autowired
//    private ReservationRepository reservationRepository;
//
//    @BeforeEach
//    void saveReservationList() {
//        Reservation reservation1 = Reservation.builder()
//                .usersId(1L)
//                .checkInDate(LocalDate.now())
//                .checkOutDate(LocalDate.now().plusDays(5))
//                .companyId(1L)
//                .dogCount(5)
//                .build();
//        Reservation reservation2 = Reservation.builder()
//                .usersId(2L)
//                .checkInDate(LocalDate.now().plusDays(1))
//                .checkOutDate(LocalDate.now().plusDays(3))
//                .companyId(2L)
//                .dogCount(6)
//                .build();
//        Reservation reservation3 = Reservation.builder()
//                .usersId(3L)
//                .checkInDate(LocalDate.now().plusDays(3))
//                .checkOutDate(LocalDate.now().plusDays(7))
//                .companyId(2L)
//                .dogCount(8)
//                .build();
//
//        reservationRepository.save(reservation1);
//        reservationRepository.save(reservation2);
//        reservationRepository.save(reservation3);
//    }
//
//    @Test
//    void findByCheckInCheckOut() {
//
//        List<Reservation> list =
//                reservationRepository.findByCheckInCheckOut( 2L);
//
//        assertThat(list.size()).isEqualTo(1);
//    }
//
////    @Test
////    void occupiedRoomCountTest() {
////        AvailableReservationHelper reservationHelper = new AvailableReservationHelper();
////
////        List<Reservation> list =
////                reservationRepository.findByCheckInCheckOut(2L);
////        Integer count = reservationHelper.occupiedRoomCount(list, LocalDate.now().plusDays(1), LocalDate.now().plusDays(6));
////        System.out.println(count);
////    }
//
////    @Test
////    void getOccupiedMap() {
////        AvailableReservationHelper reservationHelper = new AvailableReservationHelper();
////        List<Reservation> list =
////                reservationRepository.findByCheckInCheckOut(2L);
////        Map<LocalDate, Integer> integerMap = reservationHelper.getOccupiedMap(list, LocalDate.now().plusDays(1), LocalDate.now().plusDays(6));
////
////        Integer sum = 0;
////        for (LocalDate localDate : integerMap.keySet()) {
////            Long period = localDate.until(LocalDate.now(), ChronoUnit.DAYS);
////            System.out.println("########"+period);
////            System.out.println("######## count"+integerMap.get(localDate));
////            System.out.println(" ");
////        }
////    }
//}
