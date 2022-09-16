package com.team012.server.companyPosts.dto;

import com.team012.server.company.entity.Company;
import com.team012.server.companyEtc.entity.CompanyPostsImg;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class CompanyPostsDto {

    @Getter
    public static class PostDto {
        private String title;
        private String content;
        private String address;
        private Long companyId;
        private List<String> postTags;
        private List<String> availableServiceTags;
    }

    @Getter
    public static class PatchDto {

        private String title;
        private String content;
        private String address;
        private Long companyId;
        private List<String> postTags;
        private List<String> availableServiceTags;
    }

    @Getter
    @Builder
    public static class ResponseDto {
        private Long id;
        private String title;
        private String content;
        private String address;
        private Long companyId;
        private List<CompanyPostsImg> companyPostsImgList;
        private List<String> postTags;
        private List<String> availableServiceTags;

        public void setPostTags(List<String> postTags) {
            this.postTags = postTags;
        }

        public void setAvailableServiceTags(List<String> availableServiceTags) {
            this.availableServiceTags = availableServiceTags;
        }
    }
}
