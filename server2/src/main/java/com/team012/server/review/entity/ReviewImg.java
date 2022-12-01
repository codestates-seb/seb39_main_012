package com.team012.server.review.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "img_url")
    @Lob
    private String imgUrl;

    @ManyToOne
    @JoinColumn(name = "review_id")
    @JsonIgnore
    private Review review;
}
