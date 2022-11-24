package com.team012.server.reservation.helper;

import com.team012.server.reservation.entity.BookingInfo;
import com.team012.server.reservation.entity.Reservation;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class AvailableReservationHelper {

    //선택한 날짜사이에 예약되어 있는 최대 강아지 수
    public Integer occupiedRoomCount(List<Reservation>reservations,
                                      String roomName,
                                      LocalDate checkInDate,
                                      LocalDate checkOutDate) {
        Map<LocalDate, Integer> occupiedMap = getOccupiedMap(reservations, roomName,checkInDate, checkOutDate);
        Set<LocalDate> dateSet = occupiedMap.keySet();

        Integer max = 0;
        for (LocalDate localDate : dateSet) {
            Integer bookedQuantity = occupiedMap.get(localDate);

            if(max <= bookedQuantity) {
                max = bookedQuantity;
            }
        }
        return max;
    }

    //선택한 날짜 사이에 일별마다 예약된 강아지수
    public Map<LocalDate, Integer> getOccupiedMap(List<Reservation>reservations,
                                               String roomName,
                                               LocalDate checkInDate,
                                               LocalDate checkOutDate) {

        Map<LocalDate, Integer> occupiedMap = new HashMap<>();
        for(LocalDate date = checkInDate; date.isBefore(checkOutDate); date = date.plusDays(1)) {
            LocalDate currentDate = date;
            Integer occupied = reservations.stream()
                    .filter(reservation -> isBetween(currentDate, reservation))

                    .mapToInt(room -> { //whyrano....
                        List<BookingInfo> list = room.getBookingInfos();
                        int count = 0;
                        for (BookingInfo bookingInfo : list)
                            if (bookingInfo.getRoomName().equals(roomName)) {
                                count = bookingInfo.getCount();
                            }
                        return count;
                    }).sum();

            occupiedMap.put(currentDate, occupied);
        }
        return occupiedMap;
    }

    public boolean isBetween(LocalDate date, Reservation reservation) {
        LocalDate checkInDate = reservation.getCheckInDate();
        LocalDate checkOutDate = reservation.getCheckOutDate();
        return date.isBefore(checkOutDate) && date.isAfter(checkInDate);
    }
}
