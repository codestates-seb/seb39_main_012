package com.team012.server.users.service;

import com.team012.server.users.entity.DogCard;
import com.team012.server.users.entity.Users;
import com.team012.server.users.repository.DogCardRepository;
import com.team012.server.utils.aws.service.AwsS3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
@Transactional
@RequiredArgsConstructor
@Service
public class DogCardService {

    private final DogCardRepository dogCardRepository;
    private final AwsS3Service awsS3Service;

    public DogCard savedDogCard(DogCard dogCard, MultipartFile file, Users users) {


        String url = awsS3Service.singleUploadFile(file);

        dogCard.setPhotoImgUrl(url);
        dogCard.setUsers(users);

        dogCardRepository.save(dogCard);

        return dogCard;
    }

    public DogCard updateDogCard(long dogCardId,DogCard dogCard,MultipartFile file, Users users ) {


        Optional<DogCard> f2indDogCard = dogCardRepository.findById(dogCardId);
        DogCard findDogCard = f2indDogCard.orElseThrow(RuntimeException::new);

        String url = awsS3Service.singleUploadFile(file);

        Optional.ofNullable(dogCard.getDogName())
                .ifPresent(findDogCard::setDogName);
        Optional.ofNullable(dogCard.getType())
                .ifPresent(findDogCard::setType);
        Optional.ofNullable(dogCard.getGender())
                .ifPresent(findDogCard::setGender);
        Optional.ofNullable(dogCard.getAge())
                .ifPresent(findDogCard::setAge);
        Optional.ofNullable(dogCard.getEtc())
                .ifPresent(findDogCard::setEtc);
        Optional.ofNullable(dogCard.getSnackMethod())
                .ifPresent(findDogCard::setSnackMethod);
        Optional.ofNullable(dogCard.getBark())
                .ifPresent(findDogCard::setBark);
        Optional.ofNullable(dogCard.getSurgery())
                .ifPresent(findDogCard::setSurgery);
        Optional.ofNullable(dogCard.getBowelTrained())
                .ifPresent(findDogCard::setBowelTrained);
        Optional.ofNullable(url)
                .ifPresent(findDogCard::setPhotoImgUrl);


        return dogCardRepository.save(findDogCard);
    }

    @Transactional(readOnly = true)
    public DogCard findById(Long dogCardId) {

        Optional<DogCard> findDogCard = dogCardRepository.findById(dogCardId);

        return findDogCard.orElseThrow(() -> new RuntimeException("DogCard Not Found"));

    }

    // 강아지 큐카드 유저 아이디를 통한 조회
    @Transactional(readOnly = true)
    public List<DogCard> getListDogCard(Long userId) {
        return dogCardRepository.findByUsers_Id(userId);
    }
}
