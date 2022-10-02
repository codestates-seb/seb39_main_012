package com.team012.server.review.service;

import com.team012.server.review.dto.ReviewPostsResponse;
import com.team012.server.review.repository.ReviewRepository;
import com.team012.server.review.dto.ReviewCreateRequestDto;
import com.team012.server.review.dto.ReviewPatchRequestDto;
import com.team012.server.review.entity.Review;
import com.team012.server.review.repository.ReviewImgRepository;
import com.team012.server.common.aws.service.AwsS3Service;
import com.team012.server.review.entity.ReviewImg;
import com.team012.server.users.entity.Users;
import com.team012.server.users.service.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Slf4j
@RequiredArgsConstructor
@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final ReviewImgRepository reviewImgRepository;

    private final UsersService usersService;
    private final AwsS3Service awsS3Service;


    public Review createReview(ReviewCreateRequestDto dto, List<MultipartFile> files, Users writeUsers) {
        List<ReviewImg> lists = awsS3Service.convertReviewImg(files);

        Long userId = writeUsers.getId();
        // json 으로 받은 데이터 추가
        Review review = Review
                .builder()
                .title(dto.getTitle())
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

    // 페이징 처리
    public Page<Review> findByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "id");

        return reviewRepository.findAll(pageable);
    }

    public List<ReviewPostsResponse> getByPage(int page, int size, List<Review> reviewList) {
        List<Review> reviewPage = findByPage(page - 1, size).getContent(); // 리뷰 리스트가 나온다

        // 새로운 배열을 만들어준다.
        List<ReviewPostsResponse> responses = new ArrayList<>();

        for (int i = 0; i < reviewList.size(); i++) {
            // 작성자 이름 찾기
            ReviewPostsResponse response = ReviewPostsResponse
                    .builder()
                    .id(reviewPage.get(i).getId())
                    .createdAt(reviewPage.get(i).getCreatedAt().format(DateTimeFormatter.ISO_DATE))
                    .modifiedAt(reviewPage.get(i).getModifiedAt().format(DateTimeFormatter.ISO_DATE))
                    .title(reviewPage.get(i).getTitle())
                    .content(reviewPage.get(i).getContent())
                    .score(reviewPage.get(i).getScore())
                    .writer(usersService.findUsersById(reviewList.get(i).getUserId()).getUsername())
                    .reviewImgList(reviewImgRepository.findByReview_Id(reviewList.get(i).getId()))
                    .build();

            responses.add(response);
        }

        return responses;
    }

    public List<ReviewImg> findByImg(Long reviewId) {
        return reviewImgRepository.findByReview_Id(reviewId);
    }
}
