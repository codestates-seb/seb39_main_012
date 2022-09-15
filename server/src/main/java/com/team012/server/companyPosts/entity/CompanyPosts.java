package com.team012.server.companyPosts.entity;

import com.team012.server.company.entity.Company;
import com.team012.server.companyEtc.entity.CompanyPostsImg;
import com.team012.server.companyEtc.entity.CompanyRoom;
import com.team012.server.like.entity.Like;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class CompanyPosts {

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

    @OneToOne
    @JoinColumn(name = "company_room_id")
    private CompanyRoom companyRoom;

    @OneToMany(mappedBy = "companyPosts")
    private List<CompanyPostsImg> companyPostsImgList;

    @OneToMany(mappedBy = "companyPosts")
    private List<Like> likeList;

    @Builder
    public CompanyPosts(String title, String content,
                        String address, Company company,
                        CompanyRoom companyRoom) {
        this.title = title;
        this.content = content;
        this.address = address;
        this.company = company;
        this.companyRoom = companyRoom;
    }

    //    @OneToMany(mappedBy = "companyPosts")
//    private List<CompanyServiceTag> companyServiceTagList;

}
