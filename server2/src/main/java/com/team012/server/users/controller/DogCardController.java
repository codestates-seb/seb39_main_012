package com.team012.server.users.controller;

import com.team012.server.users.dto.DogCardCreateDto;
import com.team012.server.users.entity.DogCard;
import com.team012.server.common.config.userDetails.PrincipalDetails;
import com.team012.server.common.response.SingleResponseDto;
import com.team012.server.users.dto.DogCardResponseDto;
import com.team012.server.users.mapper.DogCardMapper;
import com.team012.server.users.service.DogCardService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.team012.server.common.utils.constant.Constant.*;

@RequestMapping("/v1/customer/dogCard")
@RestController
@RequiredArgsConstructor
@Slf4j
public class DogCardController {

    private final DogCardMapper mapper;
    private final DogCardService dogCardService;

    // 강아지 큐카드 생성
    @PostMapping("/create")
    public ResponseEntity createCard(
            @RequestPart(name = "dogCardDto") DogCardCreateDto dogCardDto,
                                     @RequestPart(value = "file") MultipartFile file,
                                     @AuthenticationPrincipal PrincipalDetails principalDetails) {

        DogCard dogCard = mapper.dogDtoToDogCard(dogCardDto);
        dogCardService.savedDogCard(dogCard, file, principalDetails.getUsers());

        log.info(DOG_NAME_LOG.getMessage(), dogCard.getDogName());
        log.info(DOG_TYPE_LOG.getMessage(), dogCard.getType());
        log.info(DOG_IMAGE_URL.getMessage(), dogCard.getPhotoImgUrl());

        return new ResponseEntity<>(new SingleResponseDto<>(CREATE_SUCCESS.getMessage()), HttpStatus.CREATED);
    }

    // 강아지 큐카드 수정
    @PatchMapping("/{dog-card-id}")
    public ResponseEntity patchCard(@PathVariable("dog-card-id") long dogCardId,
                                    @RequestPart(value = "dogCardDto") DogCardCreateDto dogCardDto,
                                    @RequestPart(value = "file", required = false) MultipartFile file,
                                    @AuthenticationPrincipal PrincipalDetails principalDetails) {

        DogCard dogCard = mapper.dogDtoToDogCard(dogCardDto);

        dogCardService.updateDogCard(dogCardId, dogCard, file, principalDetails.getUsers());

        return new ResponseEntity<>(new SingleResponseDto<>
                (MODIFIED_SUCCESS.getMessage()), HttpStatus.CREATED);
    }

    // 강아지 카드 상세 조회
    @GetMapping("/{dog-card-id}")
    public ResponseEntity findCard(@PathVariable("dog-card-id") long dogCardId,
                                   @AuthenticationPrincipal PrincipalDetails principalDetails) {

        DogCardResponseDto response = mapper.dogCardToDtoResponse(dogCardService.findMyDogCardById(dogCardId, principalDetails));
        return new ResponseEntity<>(new SingleResponseDto(response), HttpStatus.OK);
    }


    // 강아지 카드 삭제
    @DeleteMapping("/{dogCardId}")
    public ResponseEntity deleteCard(@PathVariable("dogCardId") Long dogCardId) {

        dogCardService.deleteDogCard(dogCardId);

        return new ResponseEntity<>(DELETE_SUCCESS.getMessage(), HttpStatus.NO_CONTENT);
    }

    // 유저가 가지고 있는 강아지 전체 조회
    @GetMapping
    public ResponseEntity findByUsersIdDogCard(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = principalDetails.getUsers().getId();
        List<DogCard> dogCardList = dogCardService.getListDogCard(userId);
        return new ResponseEntity<>(dogCardList, HttpStatus.OK);
    }

}
