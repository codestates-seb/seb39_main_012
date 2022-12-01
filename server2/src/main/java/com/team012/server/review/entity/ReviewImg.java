package com.team012.server.review.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FILE_NAME")
    private String fileName;

    @Column(name = "IMAGE_URL")
    @Lob
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "Review_Id")
    @JsonIgnore
    private Review review;
}
