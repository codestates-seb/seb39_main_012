package com.team012.server.users.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsersMessageResponseDto {
    // 유저 상태를 나타내기 워한 메세지 ex ) 가입완료..! / 아이디를 다시 확인해주세요..!
    private String message;
}
