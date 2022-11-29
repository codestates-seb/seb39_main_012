package com.team012.server.posts.dto;

import lombok.Builder;
import lombok.Getter;

public class PostsReservationListDto {

    @Getter
    public static class BookedList {
        private final String title;
        private final Integer roomPrice;
        private final String url;

        @Builder
        public BookedList(String title, Integer roomPrice, String url) {
            this.title = title;
            this.roomPrice = roomPrice;
            this.url = url;
        }
    }

    @Getter
    public static class BookedListAfterCheckOut {
        private final Long postsId;
        private final String title;
        private final Integer roomPrice;
        private final String url;

        @Builder
        public BookedListAfterCheckOut(Long postsId, String title, Integer roomPrice, String url) {
            this.postsId = postsId;
            this.title = title;
            this.roomPrice = roomPrice;
            this.url = url;
        }
    }




}
