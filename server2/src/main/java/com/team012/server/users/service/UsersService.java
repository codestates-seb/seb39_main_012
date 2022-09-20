package com.team012.server.users.service;

import com.team012.server.users.dto.UsersDto;
import com.team012.server.users.entity.Users;
import com.team012.server.users.repository.UsersRepository;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.reservation.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UsersService {
    private final UsersRepository usersRepository;
    private final ReservationRepository reservationRepository;

//    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersService(UsersRepository usersRepository, ReservationRepository reservationRepository) {
        this.usersRepository = usersRepository;
        this.reservationRepository = reservationRepository;
    }

    // 업체 이름으로 조회
    public Users getCompany(String companyName) {
        Users company = usersRepository.findByCompanyName(companyName);
        return company;
    }

    // 이메일 중복체크
    public Boolean getEmail(String email) {
        Users users = usersRepository.findByEmail(email);
        return users != null;
    }

    // Company 회원가입..
    public Users createCompany(UsersDto.CompanyPost dto) {
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
                .role(dto.getRole())
                .build();

        // DB에 저장
        usersRepository.save(savedCompany);

        return savedCompany;
    }

    // Customer 회원가입
    public Users createCustomer(UsersDto.CustomerPost dto) {
        Users savedCustomer
                = Users.builder()
                .email(dto.getEmail())
                .password(dto.getPassword())
                .username(dto.getUsername())
                .phone(dto.getPhone())
                .role(dto.getRole())
                .build();

        // DB에 저장
        usersRepository.save(savedCustomer);

        return savedCustomer;
    }

    // 회사 정보 조회
    public ArrayList<UsersDto.ReservationList> getReservationList(Long userId, Integer size, Integer page) {
        // 예약 정보들
        List<Reservation> reservationList = reservationRepository.findByUsers_Id(userId);

        // 회사관련 정보
        Users company = usersRepository.findById(userId).orElse(null);

        ArrayList<UsersDto.ReservationList> list = new ArrayList<>();

        for (Reservation reservation : reservationList) {
            UsersDto.ReservationList response =
                    UsersDto.ReservationList
                            .builder()
                            .id(reservation.getId())
                            .companyName(Objects.requireNonNull(company).getCompanyName())
                            .checkIn(reservation.getCheckIn().toString())
                            .checkOut(reservation.getCheckOut().toString())
                            .user(reservation.getUsers().getUsername())
                            .build();

            list.add(response);
        }

        return list;
    }


}
