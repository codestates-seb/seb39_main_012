package com.team012.server.like.service;

import com.team012.server.like.entity.Likes;
import com.team012.server.like.repository.LikesRepository;
import com.team012.server.posts.entity.Posts;
import com.team012.server.posts.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@AllArgsConstructor
public class LikesService {

    private final LikesRepository likeRepository;
    private final PostsService postsService;
    public boolean booleanLike(Long usersId, Long postsId) {


        Posts posts = postsService.findById(postsId);

        if (isNotAlreadyLike(usersId, postsId)) {
            posts.setLikesCount(posts.getLikesCount() + 1);
            likeRepository.save(new Likes(usersId, postsId));
            postsService.savedLikesCount(posts);
            return true;

        } else {
            posts.setLikesCount(posts.getLikesCount() - 1);
            likeRepository.delete(findLikes(usersId, postsId));
            postsService.savedLikesCount(posts);
        }
        return false;
    }

    @Transactional(readOnly = true)
    private boolean isNotAlreadyLike(Long usersId, Long postsId) {
        return likeRepository.findByUsersIdAndPostsId(usersId, postsId).isEmpty();
    }

    @Transactional(readOnly = true)
    private boolean isAlreadyLike(long likesId) {
        return likeRepository.findById(likesId).isPresent();
    }

    @Transactional(readOnly = true)
    private Likes findLikes(Long usersId, Long postsId) {
        return likeRepository.findByUsersIdAndPostsId(usersId, postsId).orElse(null);
    }
}
