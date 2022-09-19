package com.team012.server.users.dto;

import com.team012.server.users.entity.Users;
import com.team012.server.posts.entity.Posts;
import com.team012.server.reply.entity.CompanyReply;
import com.team012.server.reservation.entity.Reservation;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.List;

public class UsersDto {

    @Getter
    @Setter
    @Validated
    @NoArgsConstructor
    public static class Post {
        @NotBlank(message = "이메일을 입력해주세요.")
        @Email
        private String email;

        @NotBlank(message = "비밀번호를 제대로 입력해주세요.")
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
                message = "비밀번호는 영어, 숫자, 특수문자로 8에서 16자리로 구성되어야 합니다.")
        private String password;

        @NotBlank(message = "이름을 입력해주세요.")
        private String username;

        @NotBlank(message = "회사이름을 입력해주세요.")
        private String companyName;

        @NotBlank(message = "전화번호 입력해주세요.")
        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
        private String phone;

        @NotBlank(message = "주소를 입력해주세요.")
        private String address;

        @Builder
        public Post(String email, String password, String username,
                    String companyName, String phone, String address) {
            this.email = email;
            this.password = password;
            this.username = username;
            this.companyName = companyName;
            this.phone = phone;
            this.address = address;
        }
    }

    @Getter
    @Setter
    @Validated
    @NoArgsConstructor
    public static class CheckEmail {
        @Email
        @NotBlank(message = "이메일을 입력해주세요.")
        private String email;

        @Builder
        public CheckEmail(String email) {
            this.email = email;
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

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CompanyReservationInfoResponse {
        private Users companyInfo;

        private List<Reservation> reservationList;

        @Builder
        public CompanyReservationInfoResponse(Users companyInfo, List<Reservation> reservationList) {
            this.companyInfo = companyInfo;
            this.reservationList = reservationList;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class CompanyInfoResponse {
        private String email;
        private String companyName;
        private String address;
        private String username;
        private String phone;
        private LocalDateTime createdAt;
        private List<CompanyReply> replyList;
        private Posts posts;
        private List<Reservation> reservationList;

        @Builder
        public CompanyInfoResponse(String email, String companyName, String address,
                                   String username, String phone,
                                   LocalDateTime createdAt, List<CompanyReply> replyList,
                                   Posts posts, List<Reservation> reservationList) {
            this.email = email;
            this.companyName = companyName;
            this.address = address;
            this.username = username;
            this.phone = phone;
            this.createdAt = createdAt;
            this.replyList = replyList;
            this.posts = posts;
            this.reservationList = reservationList;
        }
    }
}
