package com.team012.server.users.service;

import com.team012.server.users.dto.UsersDto;
import com.team012.server.users.entity.Users;
import com.team012.server.users.repository.UsersRepository;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final ReservationRepository reservationRepository;
//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersService(UsersRepository usersRepository, ReservationRepository reservationRepository) {
        this.usersRepository = usersRepository;
        this.reservationRepository = reservationRepository;
    }

    // Company 단건 조회
    public Users getCompany(Long companyId) {
        return usersRepository.findById(companyId).orElse(null);
    }

    // Company 회원가입..
    public Users createCompany(UsersDto.Post dto) {
        // 비밀번호 암호화
//        String encPassword = bCryptPasswordEncoder.encode(company.getPassword());
        // 객체에 반영

        Users savedCompany
                = Users.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .companyName(dto.getCompanyName())
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .role("ROLE_COM")
                .build();
        // DB에 저장
        usersRepository.save(savedCompany);

        return savedCompany;
    }

    public Users checkEmail(String email) {
        Optional<Users> findCompany = Optional.ofNullable(usersRepository.findByEmail(email));
        return findCompany.orElse(null);
    }

    // 회사 정보 조회
    public UsersDto.CompanyInfoResponse infoCompany(Long companyId) {
        // 예약 정보들
        List<Reservation> reservationList = reservationRepository.findByCompany_Id(companyId);

        // 회사관련 정보
        Users company = usersRepository.findById(companyId).orElse(null);
        UsersDto.CompanyInfoResponse companyInfoResponse =
                UsersDto.CompanyInfoResponse.builder()
                        .createdAt(company.getCreatedAt())
                        .email(company.getEmail())
                        .companyName(company.getCompanyName())
                        .address(company.getAddress())
                        .username(company.getUsername())
                        .phone(company.getPhone())
                        .replyList(company.getCompanyReplyList())
                        .reservationList(reservationList)
                        .build();

//        CompanyDto.CompanyReservationInfoResponse response
//                = CompanyDto.CompanyReservationInfoResponse.builder()
//                .companyInfo(company)
//                .reservationList(reservationList)
//                .build();

        return companyInfoResponse;
    }
}
