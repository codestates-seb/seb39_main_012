package com.team012.server.customer.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class DogCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @Lob
    @Column(name = "photo_img_url")
    private String photoImgUrl;

    @Column(name = "dog_name")
    private String dogName;

    @Column(name = "type") // 강아지 종
    private String type;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Lob // 건강 특이사항
    @Column(name = "etc")
    private String etc;

    // 사료급여 방식
    @Column(name = "feed_method")
    private Boolean feedMethod;

    // 간식급여 여부
    @Column(name = "snack_method")
    private Boolean snackMethod;

    // 입질, 짖음 여부
    @Column(name = "bark")
    private Boolean bark;

    // 중성화 여부
    @Column(name = "surgery")
    private Boolean surgery;

    // 실외 / 실내배변 여부
    @Column(name = "bowel_trained")
    private Boolean bowelTrained;

    // 강아지 큐카드는 한명의 회원이 가질 수 있다.
    @ManyToOne // 회원 삭제시 강아지 큐카드도 삭제
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
