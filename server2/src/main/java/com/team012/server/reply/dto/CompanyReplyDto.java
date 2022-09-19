package com.team012.server.reply.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CompanyReplyDto {

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Post {
        private Long customerReviewId;
        private String content;

        @Builder
        public Post(String content) {
            this.content = content;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Patch {
        private Long replyId;
        private String content;

        @Builder
        public Patch(String content) {
            this.content = content;
        }
    }
}
