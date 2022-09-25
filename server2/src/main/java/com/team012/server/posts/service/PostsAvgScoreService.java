package com.team012.server.posts.service;

import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.entity.PostsAvgScore;
import com.team012.server.posts.repository.PostsAvgScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsAvgScoreService {

    private final PostsAvgScoreRepository postsAvgScoreRepository;

    private final PostsService postsService;

    // postsAvgScore 모델에 저장
    public PostsAvgScore savedPostsScore(Integer score, Long postsId) {
        PostsAvgScore avg = PostsAvgScore
                .builder()
                .score(score)
                .postsId(postsId)
                .build();

        // 별점 최신화
        averageCompanyScore(postsId);

        return postsAvgScoreRepository.save(avg);
    }

    // 리뷰 점수 평균 만들기(메인 화면에 보여주는 부분)
    public void averageCompanyScore(Long postsId) {
        double totalScore = 0.0;

        // 회사에 등록된 리뷰정보 가져오기
        List<PostsAvgScore> scoreList = postsAvgScoreRepository.findByPostsId(postsId);
        for (PostsAvgScore postsAvgScore : scoreList) {
            totalScore += postsAvgScore.getScore();
        }

        Double avg = totalScore / scoreList.size();

        Posts updated = postsService.findById(postsId);
        updated.setAvgScore(avg);

        postsService.save(updated);

    }

}
