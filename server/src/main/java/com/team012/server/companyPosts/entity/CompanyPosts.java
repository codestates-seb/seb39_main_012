package com.team012.server.companyPosts.entity;

import com.team012.server.baseEntity.BaseEntity;
import com.team012.server.company.entity.Company;
import com.team012.server.companyEtc.entity.CompanyPostsImg;
import com.team012.server.companyEtc.entity.CompanyRoom;
import com.team012.server.like.entity.Like;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CompanyPosts extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "address")
    private String address;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    @JoinColumn(name = "company_room_id")
    private CompanyRoom companyRoom;

    @OneToMany(mappedBy = "companyPosts",cascade = CascadeType.REMOVE)
    private List<CompanyPostsImg> companyPostsImgList;

    @OneToMany(mappedBy = "companyPosts",cascade = CascadeType.REMOVE)
    private List<Like> likeList;

    private String companyServiceTagList;

    public void stringToList(String companyServiceTagList) {
        List<String> list = new ArrayList<>();
    }

    @Builder
    public CompanyPosts(String title, String content, String address, String companyServiceTagList) {
        this.title = title;
        this.content = content;
        this.address = address;
        this.companyServiceTagList = companyServiceTagList;
    }

//일단 CompanyRoom은 생성자에서 제외시킴 추후 작업 예정

    //    @OneToMany(mappedBy = "companyPosts")
//    private List<CompanyServiceTag> companyServiceTagList;

}
