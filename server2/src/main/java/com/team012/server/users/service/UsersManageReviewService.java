package com.team012.server.users.service;

import com.team012.server.common.exception.BusinessLogicException;
import com.team012.server.common.exception.ExceptionCode;
import com.team012.server.company.entity.Company;
import com.team012.server.company.repository.CompanyRepository;
import com.team012.server.posts.dto.PostsReviewInfo;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.repository.PostsRepository;
import com.team012.server.reservation.entity.Reservation;
import com.team012.server.reservation.repository.ReservationRepository;
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

    private final ReservationRepository reservationRepository;

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
        for (Review review : reviewList) {
            Posts posts = postsRepository.findById(review.getPostsId())
                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.POST_NOT_FOUND));

            Company company = companyRepository.findById(posts.getCompanyId())
                    .orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMPANY_NOT_FOUND));

            Reservation reservation = reservationRepository.findByCompanyId(Objects.requireNonNull(company).getId());

            PostsReviewInfo postsReviewInfo = postsReviewInfo(posts, company, reservation);
            ReviewInfoDto reviewInfoDto = reviewInfoDto(review, postsReviewInfo);

            response.add(reviewInfoDto);
        }
        return response;
    }
    private PostsReviewInfo postsReviewInfo(Posts posts, Company company, Reservation reservation) {
        String companyName = company.getCompanyName();
        String postsImgUrl = posts.getPostsImgList().get(0).getImgUrl();
        int totalPrice = reservation.getTotalPrice();

        return PostsReviewInfo
                .builder()
                .postsCompanyName(companyName)
                .postsImg(postsImgUrl)  // 첫번째 이미지만 준다.
                .totalPrice(totalPrice)
                .build();
    }

    private ReviewInfoDto reviewInfoDto(Review review, PostsReviewInfo postsReviewInfo) {
        String createdAt = review.getCreatedAt().format(DateTimeFormatter.ISO_DATE);
        String modifiedAt = review.getModifiedAt().format(DateTimeFormatter.ISO_DATE);


        return ReviewInfoDto
                .builder()
                .createdAt(createdAt)
                .modifiedAt(modifiedAt)
                .id(review.getId())
                .title(review.getTitle())
                .content(review.getContent())
                .score(review.getScore())
                .userId(review.getUserId())
                .reviewImg(getListReviewImgList(review.getId()))
                .companyInfo(postsReviewInfo)
                .build();
    }
}
