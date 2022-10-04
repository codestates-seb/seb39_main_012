package com.team012.server.reservation.repository;

import com.team012.server.reservation.entity.ReservationList;
import com.team012.server.reservation.helper.AvailableReservationHelper;
import com.team012.server.reservation.repository.ReservationListRepository;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ReservationListTest {

    @Autowired
    private ReservationListRepository reservationListRepository;

    @BeforeEach
    void saveReservationList() {
        ReservationList reservationList1 = ReservationList.builder()
                .usersId(1L)
                .checkInDate(LocalDate.now())
                .checkOutDate(LocalDate.now().plusDays(5))
                .companyId(1L)
                .dogCount(5)
                .build();
        ReservationList reservationList2 = ReservationList.builder()
                .usersId(2L)
                .checkInDate(LocalDate.now().plusDays(1))
                .checkOutDate(LocalDate.now().plusDays(3))
                .companyId(2L)
                .dogCount(6)
                .build();
        ReservationList reservationList3 = ReservationList.builder()
                .usersId(3L)
                .checkInDate(LocalDate.now().plusDays(3))
                .checkOutDate(LocalDate.now().plusDays(7))
                .companyId(2L)
                .dogCount(8)
                .build();

        reservationListRepository.save(reservationList1);
        reservationListRepository.save(reservationList2);
        reservationListRepository.save(reservationList3);
    }

    @Test
    void findByCheckInCheckOut() {

        List<ReservationList> list =
                reservationListRepository.findByCheckInCheckOut( 2L);

        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void occupiedRoomCountTest() {
        AvailableReservationHelper reservationHelper = new AvailableReservationHelper();

        List<ReservationList> list =
                reservationListRepository.findByCheckInCheckOut(2L);
        Integer count = reservationHelper.occupiedRoomCount(list, LocalDate.now().plusDays(1), LocalDate.now().plusDays(6));
        System.out.println(count);
    }

    @Test
    void getOccupiedMap() {
        AvailableReservationHelper reservationHelper = new AvailableReservationHelper();
        List<ReservationList> list =
                reservationListRepository.findByCheckInCheckOut(2L);
        Map<LocalDate, Integer> integerMap = reservationHelper.getOccupiedMap(list, LocalDate.now().plusDays(1), LocalDate.now().plusDays(6));

        Integer sum = 0;
        for (LocalDate localDate : integerMap.keySet()) {
            Long period = localDate.until(LocalDate.now(), ChronoUnit.DAYS);
            System.out.println("########"+period);
            System.out.println("######## count"+integerMap.get(localDate));
            System.out.println(" ");
        }
    }
}
