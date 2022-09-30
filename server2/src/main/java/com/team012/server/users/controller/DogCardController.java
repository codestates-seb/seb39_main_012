package com.team012.server.users.controller;

import com.team012.server.users.entity.DogCard;
import com.team012.server.common.config.userDetails.PrincipalDetails;
import com.team012.server.common.response.SingleResponseDto;
import com.team012.server.users.dto.DogCardDto;
import com.team012.server.users.mapper.DogCardMapper;
import com.team012.server.users.service.DogCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/v1/customer/dogCard")
@RestController
@RequiredArgsConstructor
@Slf4j
public class DogCardController {

    private final DogCardMapper mapper;
    private final DogCardService dogCardService;

    // 강아지 큐카드 생성
    @PostMapping("/create")
    public ResponseEntity createCard(@RequestPart(value = "dogCardDto") DogCardDto.Post dogCardDto,
                                     @RequestPart(value = "file") MultipartFile file,
                                     @AuthenticationPrincipal PrincipalDetails principalDetails) {

        DogCard dogCard = mapper.dogDtoToDogCard(dogCardDto);
        dogCardService.savedDogCard(dogCard, file, principalDetails.getUsers());

        log.info("dogName = {}", dogCard.getDogName());
        log.info("dogType = {}", dogCard.getType());
        log.info("dogImageUrl = {}", dogCard.getPhotoImgUrl());

        return new ResponseEntity<>(new SingleResponseDto<>("create success"), HttpStatus.CREATED);
    }

    // 강아지 큐카드 수정
    @PatchMapping("/{dog-card-id}")
    public ResponseEntity patchCard(@PathVariable("dog-card-id") long dogCardId,
                                    @RequestPart(value = "dogCardDto") DogCardDto.Post dogCardDto,
                                    @RequestPart(value = "file") MultipartFile file,
                                    @AuthenticationPrincipal PrincipalDetails principalDetails) {

        DogCard dogCard = mapper.dogDtoToDogCard(dogCardDto);

        dogCardService.updateDogCard(dogCardId, dogCard, file, principalDetails.getUsers());

        return new ResponseEntity<>(new SingleResponseDto<>
                ("patch success"), HttpStatus.CREATED);
    }

    // 강아지 카드 상세 조회
    @GetMapping("/{dog-card-id}")
    public ResponseEntity findCard(@PathVariable("dog-card-id") long dogCardId) {

        DogCardDto.Response response = mapper.dogCardToDtoResponse(dogCardService.findById(dogCardId));
        return new ResponseEntity<>(new SingleResponseDto(response), HttpStatus.OK);
    }

    // 강아지 카드 삭제
    @DeleteMapping("/{dogCardId}")
    public ResponseEntity deleteCard(@PathVariable("dogCardId") Long dogCardId) {

        dogCardService.deleteDogCard(dogCardId);

        DogCardDto.Message response = DogCardDto.Message
                .builder()
                .message("삭제완료..!")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
