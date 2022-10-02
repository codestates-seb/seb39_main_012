package com.team012.server.reservation.service;

import com.team012.server.common.config.userDetails.PrincipalDetails;
import com.team012.server.company.entity.Company;
import com.team012.server.company.service.CompanyService;
import com.team012.server.reservation.dto.TotalReservationDto;
import com.team012.server.reservation.entity.ReservationList;
import com.team012.server.reservation.repository.ReservationListRepository;
import com.team012.server.users.entity.DogCard;
import com.team012.server.users.service.DogCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationConfirmService {

    private final CompanyService companyService;
    private final ReservationListRepository reservationListRepository;
    private final DogCardService dogCardService;


    public List<TotalReservationDto> confirmReservation(PrincipalDetails principalDetails, Long reservationId) {

        List<TotalReservationDto> confirmReservationList = new ArrayList<>();

        Long userId = principalDetails.getUsers().getId();

        Optional<ReservationList> byUsersIdAndReservedId = reservationListRepository.findByUsersIdAndReservedId(userId, reservationId);

        ReservationList findReservation = byUsersIdAndReservedId.orElse(null);



        // 예약한 강아지 한마리 마다 확인.
        for (int i = 0; i < findReservation.getDogIdList().size(); i++) {
            Long aLong = findReservation.getDogIdList().get(i);
            DogCard findDogCard = dogCardService.findById(aLong);

            // 회사정보 조회
            Company companyByCompanyId = companyService.getCompanyByCompanyId(findReservation.getCompanyId());


            TotalReservationDto reservationDogCard = TotalReservationDto.builder()
                    .dogName(findDogCard.getDogName())
                    .photoImgUrl(findDogCard.getPhotoImgUrl())
                    .type(findDogCard.getType())
                    .gender(findDogCard.getGender())
                    .age(findDogCard.getAge())
                    .weight(findDogCard.getWeight())
                    .snackMethod(findDogCard.getSnackMethod())
                    .bark(findDogCard.getBark())
                    .surgery(findDogCard.getSurgery())
                    .bowelTrained(findDogCard.getBowelTrained())
                    .etc(findDogCard.getEtc())
                    .name(findReservation.getUserInfo().getName())
                    .phone(findReservation.getUserInfo().getPhone())
                    .address(companyByCompanyId.getAddress())
                    .checkIn(findReservation.getCheckIn())
                    .checkOut(findReservation.getCheckOut())
                    .totalPrice(findReservation.getTotalPrice())
                    .build();

            confirmReservationList.add(reservationDogCard);

        }
        return confirmReservationList;
    }
}
