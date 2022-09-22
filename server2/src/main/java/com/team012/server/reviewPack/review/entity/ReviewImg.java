package com.team012.server.reviewPack.review.entity;

import com.team012.server.reviewPack.review.entity.Review;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ReviewImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Column(name = "img_url")
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "review_id")
    private Review review;

    @Builder
    public ReviewImg(String fileName, String imgUrl) {
        this.fileName = fileName;
        this.imgUrl = imgUrl;
    }
}
