package com.team012.server.users.service;

import com.team012.server.common.exception.BusinessLogicException;
import com.team012.server.common.exception.ExceptionCode;
import com.team012.server.company.service.CompanyService;
import com.team012.server.users.dto.CustomerSignUpRequestDto;
import com.team012.server.users.dto.CustomerUpdateRequestDto;
import com.team012.server.users.entity.UsersImg;
import com.team012.server.users.repository.UsersImgRepository;
import com.team012.server.users.repository.UsersRepository;
import com.team012.server.users.dto.CompanySignUpRequestDto;
import com.team012.server.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class UsersService {
    private final UsersRepository usersRepository;

    private final CompanyService companyService;

    private final UsersImgRepository usersImgRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 이메일 중복체크
    public Boolean getEmail(String email) {
        Users users = usersRepository.findByEmail(email);
        return users != null;
    }

    // Company 회원가입..
    public Users createCompany(CompanySignUpRequestDto dto) {

        // 비밀번호 암호화
        String encPassword = bCryptPasswordEncoder.encode(dto.getPassword());

        // 객체에 반영
        Users savedCompanyUser
                = Users.builder()
                .email(dto.getEmail())
                .password(encPassword)
                .username(dto.getUsername())
                .phone(dto.getPhone())
                .roles("ROLE_COMPANY")
                .build();

        Users users = usersRepository.save(savedCompanyUser);

        companyService.createCompany(dto, users.getId());

        return savedCompanyUser;
    }

    // Customer 회원가입
    public Users createCustomer(CustomerSignUpRequestDto dto) {
        // 비밀번호 암호화
        String encPassword = bCryptPasswordEncoder.encode(dto.getPassword());

        Users savedCustomer
                = Users.builder()
                .email(dto.getEmail())
                .password(encPassword)
                .username(dto.getUsername())
                .phone(dto.getPhone())
                .roles("ROLE_CUSTOMER")
                .build();

        // DB에 저장
        usersRepository.save(savedCustomer);

        return savedCustomer;
    }

    @Transactional(readOnly = true)
    public Users findUsersById(Long usersId) {
        return usersRepository.findById(usersId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
    }

    public void updateCustomer(Long id, CustomerUpdateRequestDto dto, String imgUrl) {
        Users findUsers = usersRepository.findById(id)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));

        findUsers.setUsername(dto.getUserName());
        findUsers.setPhone(dto.getPhone());

        savedUsersImg(id, imgUrl);

        usersRepository.save(findUsers);
    }

    /// 추가 코드 ///
    public Users findByEmail(String phone) {
        return usersRepository.findByPhone(phone);
    }

    public Users findByPassword(String email) {
        return usersRepository.findByEmail(email);
    }

    // 임시비밀번호 저장
    public void setExPassword(Users users) {
        usersRepository.save(users);
    }

    // 유저 이미지 업로드
    public void savedUsersImg(Long id, String imgUrl) {
        UsersImg usersImg = UsersImg
                .builder()
                .usersId(id)
                .imgUrl(imgUrl)
                .build();
        usersImgRepository.save(usersImg);
    }

}
