package com.team012.server.users.service;

import com.amazonaws.services.ec2.model.Reservation;
import com.team012.server.company.entity.Company;
import com.team012.server.company.repository.CompanyRepository;
import com.team012.server.posts.dto.PostsReviewInfo;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.repository.PostsRepository;
import com.team012.server.reservation.entity.ReservationList;
import com.team012.server.reservation.repository.ReservationListRepository;
import com.team012.server.review.dto.ReviewInfoDto;
import com.team012.server.review.entity.Review;
import com.team012.server.review.entity.ReviewImg;
import com.team012.server.review.repository.ReviewImgRepository;
import com.team012.server.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Transactional
@RequiredArgsConstructor
@Service
@Slf4j
public class UsersManageReviewService {

    private final ReviewService reviewService;

    private final ReviewImgRepository reviewImgRepository;

    private final PostsRepository postsRepository;

    private final CompanyRepository companyRepository;

    private final ReservationListRepository reservationListRepository;

    // 작성한 리뷰 유저 아이디를 통한 조회
    @Transactional(readOnly = true)
    public List<Review> getListReview(Long userId) {
        return reviewService.getListReview(userId);
    }

    public List<ReviewImg> getListReviewImgList(Long reviewId) {
        return reviewImgRepository.findByReview_Id(reviewId);
    }

    public List<ReviewInfoDto> getListReviewInfoList(Long userId) {
        List<Review> reviewList = getListReview(userId);

        List<ReviewInfoDto> response = new ArrayList<>();

        for (int i = 0; i < reviewList.size(); i++) {
            Posts posts = postsRepository.findById(reviewList.get(i).getPostsId()).orElse(null);
            Company company = companyRepository.findById(Objects.requireNonNull(posts).getCompanyId()).orElse(null);
            ReservationList reservation = reservationListRepository.findByCompanyId(Objects.requireNonNull(company).getId());

            PostsReviewInfo postsReviewInfo = PostsReviewInfo
                    .builder()
                    .postsCompanyName(Objects.requireNonNull(company).getCompanyName())
                    .postsImg(posts.getPostsImgList().get(0).getImgUrl())  // 첫번째 이미지만 준다.
                    .totalPrice(reservation.getTotalPrice())
                    .build();


            ReviewInfoDto reviewInfoDto = ReviewInfoDto
                    .builder()
                    .createdAt(reviewList.get(i).getCreatedAt().format(DateTimeFormatter.ISO_DATE))
                    .modifiedAt(reviewList.get(i).getModifiedAt().format(DateTimeFormatter.ISO_DATE))
                    .id(reviewList.get(i).getId())
                    .title(reviewList.get(i).getTitle())
                    .content(reviewList.get(i).getContent())
                    .score(reviewList.get(i).getScore())
                    .userId(reviewList.get(i).getUserId())
                    .reviewImg(getListReviewImgList(reviewList.get(i).getId()))
                    .companyInfo(postsReviewInfo)
                    .build();

            response.add(reviewInfoDto);
        }

        return response;
    }
}
