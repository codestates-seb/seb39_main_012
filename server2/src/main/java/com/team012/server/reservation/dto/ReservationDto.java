package com.team012.server.reservation.dto;

import com.team012.server.reservation.entity.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class ReservationDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        private Long id;
        private Boolean status;

        public Patch(Boolean status) {
            this.status = status;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ReservationListResponse {
        private List<Reservation> reservationList;

        @Builder
        public ReservationListResponse(List<Reservation> reservationList) {
            this.reservationList = reservationList;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private String message;

        @Builder
        public Response(String message) {
            this.message = message;
        }
    }
}
