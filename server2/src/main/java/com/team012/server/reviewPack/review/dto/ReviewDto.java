package com.team012.server.reviewPack.review.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ReviewDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private String content;
        private Integer score;
        private Long postsId;

        @Builder
        public Post(String content, Integer score, Long postsId) {
            this.content = content;
            this.score = score;
            this.postsId = postsId;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        private Long id;
        private String content;
        private Integer score;

        @Builder
        public Patch(Long id, String content, Integer score) {
            this.id = id;
            this.content = content;
            this.score = score;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String content;
        private Integer score;

        @Builder
        public Response(Long id, String content, Integer score) {
            this.id = id;
            this.content = content;
            this.score = score;
        }
    }
}
