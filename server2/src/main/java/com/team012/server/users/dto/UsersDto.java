package com.team012.server.users.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

public class UsersDto {

    @Getter
    @Setter
    @Validated
    @NoArgsConstructor
    public static class CompanyPost {
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

        private String detailAddress;

        private String role;

        @Builder
        public CompanyPost(String email, String password, String username,
                    String companyName, String phone, String address,
                    String detailAddress, String role) {
            this.email = email;
            this.password = password;
            this.username = username;
            this.companyName = companyName;
            this.phone = phone;
            this.address = address;
            this.detailAddress = detailAddress;
            this.role = role;
        }
    }

    @Getter
    @Setter
    @Validated
    @NoArgsConstructor
    public static class CustomerPost {
        @NotBlank(message = "이메일을 입력해주세요.")
        @Email
        private String email;
        // 암호화 필요
        @NotBlank
        @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
                message = "비밀번호는 영어, 숫자, 특수문자로 8에서 16자리로 구성되어야 합니다.")
        private String password;

        @NotBlank(message = "닉네임을 입력해주세요.")
        private String username;

        @NotBlank
        @Pattern(regexp = "^010-\\d{3,4}-\\d{4}$",
                message = "휴대폰 번호는 010으로 시작하는 11자리 숫자와 '-'로 구성되어야 합니다.")
        private String phone;

        private String role;


        @Builder
        public CustomerPost(String email, String password, String username, String phone, String role) {
            this.email = email;
            this.password = password;
            this.username = username;
            this.phone = phone;
            this.role = role;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Login {

        @NotBlank(message = "아이디를 입력하세요.")
        private String username;

        @NotBlank(message = "비밀번호를 입력하세요.")
        private String password;

    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class MessageResponse {
        private String message;

        @Builder
        public MessageResponse(String message) {
            this.message = message;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class InfoResponse {
        private String username;
        private String phone;
        private String email;

        private String companyName;
        private List<UsersDto.ReservationList> reservationList;

        @Builder
        public InfoResponse(String username, String phone,
                            String email, String companyName,
                            List<UsersDto.ReservationList> reservationList) {
            this.username = username;
            this.phone = phone;
            this.email = email;
            this.companyName = companyName;
            this.reservationList = reservationList;
        }
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class ReservationList {
        private Long id;
        private String companyName;
        private String checkIn;
        private String checkOut;
        private String user;

        @Builder
        public ReservationList(Long id, String companyName, String checkIn, String checkOut, String user) {
            this.id = id;
            this.companyName = companyName;
            this.checkIn = checkIn;
            this.checkOut = checkOut;
            this.user = user;
        }
    }

}
