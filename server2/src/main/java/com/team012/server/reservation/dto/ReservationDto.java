package com.team012.server.reservation.dto;

import com.team012.server.reservation.entity.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

public class ReservationDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private Long companyId;
        private String checkIn;
        private String checkOut;
        private Integer totalPrice;

        @Builder
        public Post(Long companyId, String checkIn, String checkOut, Integer totalPrice) {
            this.companyId = companyId;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.totalPrice = totalPrice;
        }
    }

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
        private Page<Reservation> reservationList;

        @Builder
        public ReservationListResponse(Page<Reservation> reservationList) {
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
