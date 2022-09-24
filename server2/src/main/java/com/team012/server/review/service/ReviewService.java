package com.team012.server.review.service;

import com.team012.server.review.dto.ReviewCreateRequestDto;
import com.team012.server.review.dto.ReviewPatchRequestDto;
import com.team012.server.review.entity.Review;
import com.team012.server.review.repository.ReviewRepository;
import com.team012.server.review.repository.ReviewImgRepository;
import com.team012.server.utils.aws.service.AwsS3Service;
import com.team012.server.review.entity.ReviewImg;
import com.team012.server.users.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final ReviewImgRepository reviewImgRepository;

    private final AwsS3Service awsS3Service;

    public Review createReview(ReviewCreateRequestDto dto, List<MultipartFile> files, Users writeUsers) {
        List<ReviewImg> lists = awsS3Service.convertReviewImg(files);

        Long userId = writeUsers.getId();
        // json 으로 받은 데이터 추가
        Review review = Review
                .builder()
                .content(dto.getContent())
                .score(dto.getScore())
                .postsId(dto.getPostsId())
                .userId(userId)
                .build();

        // 이미지 파일 연결 & 이미지 파일 엔티티에 리뷰 아이디 연결
        review.setReviewImgList(lists);
        for (ReviewImg reviewImg : lists) {
            reviewImg.setReview(review);
        }

        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, ReviewPatchRequestDto dto, List<MultipartFile> files) {
        Review findReview = reviewRepository.findById(id).orElse(null);
        if (findReview == null) throw new NullPointerException("리뷰가 없습니다.");

        // 데이터 수정
        findReview.setContent(dto.getContent());
        findReview.setScore(dto.getScore());

        // 리뷰 이미지 검색
        List<ReviewImg> reviewImgList = reviewImgRepository.findByReview_Id(id);
        log.info("리뷰에 달린 파일들 : {}", reviewImgList);

        List<ReviewImg> lists = awsS3Service.updateReviewImg(reviewImgList, files);

        // 이미지 파일 연결 & 이미지 파일 엔티티에 리뷰 아이디 연결
        findReview.setReviewImgList(lists);
        for (ReviewImg reviewImg : lists) {
            reviewImg.setReview(findReview);
        }

        return reviewRepository.save(findReview);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Review> getListReview(Long id) {
        return reviewRepository.findByUserId(id);
    }
}
