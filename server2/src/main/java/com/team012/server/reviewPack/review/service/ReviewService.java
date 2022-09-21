package com.team012.server.reviewPack.review.service;

import com.team012.server.utils.aws.service.AwsS3Service;
import com.team012.server.reviewPack.reviewImg.entity.ReviewImg;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.repository.PostsRepository;
import com.team012.server.reviewPack.review.repository.ReviewRepository;
import com.team012.server.reviewPack.review.dto.ReviewDto;
import com.team012.server.reviewPack.review.entity.Review;
import com.team012.server.usersPack.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    private final PostsRepository postsRepository;

    private final AwsS3Service awsS3Service;

    public Review createReview(ReviewDto.Post dto, List<MultipartFile> files, Users writeUsers) {
        List<ReviewImg> lists = awsS3Service.convertReviewImg(files);

        // json 으로 받은 데이터 추가
        Review review = Review
                .builder()
                .content(dto.getContent())
                .score(dto.getScore())
                .build();

        // Posts 임의 데이터
        Posts posts = new Posts();
        Posts saved = postsRepository.save(posts);

        review.setUsers(writeUsers);
        review.setPosts(saved);

        // 이미지 파일 연결 & 이미지 파일 엔티티에 리뷰 아이디 연결
        review.setReviewImgList(lists);
        for (ReviewImg reviewImg : lists) {
            reviewImg.setReview(review);
        }

        return reviewRepository.save(review);
    }

    public Review updateReview(ReviewDto.Patch dto, List<MultipartFile> files) {
        Review updateReview = reviewRepository.findById(dto.getId()).orElse(null);
        if (updateReview == null) throw new NullPointerException();

        List<ReviewImg> lists = awsS3Service.convertReviewImg(files);
        // json 으로 받은 데이터 추가
        updateReview = Review
                .builder()
                .content(dto.getContent())
                .score(dto.getScore())
                .build();

        // 이미지 파일 연결 & 이미지 파일 엔티티에 리뷰 아이디 연결
        updateReview.setReviewImgList(lists);
        for (ReviewImg reviewImg : lists) {
            reviewImg.setReview(updateReview);
        }

        return reviewRepository.save(updateReview);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
