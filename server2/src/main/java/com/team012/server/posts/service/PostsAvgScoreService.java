package com.team012.server.posts.service;

import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.entity.PostsAvgScore;
import com.team012.server.posts.repository.PostsAvgScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsAvgScoreService {

    private final PostsAvgScoreRepository postsAvgScoreRepository;

    private final PostsService postsService;

    // postsAvgScore 모델에 저장
    public void savedPostsScore(Double score, Long postsId) {

        PostsAvgScore avg = PostsAvgScore
                .builder()
                .score(score)
                .postsId(postsId)
                .build();
        postsAvgScoreRepository.save(avg);
        // 별점 최신화
        averageCompanyScore(postsId);
    }

    // 리뷰 점수 평균 만들기(메인 화면에 보여주는 부분)
    public void averageCompanyScore(Long postsId) {
        double totalScore = 0.0;

        // 회사에 등록된 리뷰정보 가져오기
        List<PostsAvgScore> scoreList = postsAvgScoreRepository.findByPostsId(postsId);
        for (PostsAvgScore postsAvgScore : scoreList) {
            totalScore += postsAvgScore.getScore();
        }

        double avg = totalScore / scoreList.size();
        avg = Math.round(avg * 10) / 10.0;

        Posts updated = postsService.findById(postsId);
        updated.setAvgScore(avg);

        postsService.save(updated);

    }

}
