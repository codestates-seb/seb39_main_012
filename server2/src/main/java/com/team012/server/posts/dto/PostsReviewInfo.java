package com.team012.server.posts.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostsReviewInfo {

    private String postsImg;
    private String postsCompanyName;
    private Integer totalPrice;
}
