package com.team012.server.users.service;

import com.team012.server.common.config.userDetails.PrincipalDetails;
import com.team012.server.users.dto.ChangePasswordDto;
import com.team012.server.users.entity.Users;
import com.team012.server.users.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordChangeService {
    private final UsersRepository usersRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Boolean changePassword(ChangePasswordDto dto, Users users) {

        // 1. 기존 비밀번호랑 입력한 비밀번호를 비교
        boolean checkPassword = bCryptPasswordEncoder.matches(dto.getPassword(), users.getPassword());

        if (!checkPassword) return false;

        // 2. 일치하는 경우 비밀번호 변경하기 (암호화 적용)
        users.setPassword(bCryptPasswordEncoder.encode(dto.getNewPassword()));

        // 3. db에 적용
        usersRepository.save(users);

        return true;
    }


}
