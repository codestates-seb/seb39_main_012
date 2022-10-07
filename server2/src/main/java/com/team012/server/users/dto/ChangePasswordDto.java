package com.team012.server.users.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordDto {
    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 영어, 숫자, 특수문자로 8에서 16자리로 구성되어야 합니다.")
    private String password;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}",
            message = "비밀번호는 영어, 숫자, 특수문자로 8에서 16자리로 구성되어야 합니다.")
    private String newPassword;

}
