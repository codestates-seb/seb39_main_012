package com.team012.server.companyPosts.dto;

import com.team012.server.companyEtc.entity.CompanyPostsImg;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
public class CompanyPostsDto {

    @Getter
    public static class PostDto {
        @NotBlank
        private String title;
        @NotBlank
        private String content;

        private List<String> address; //0번째는 위도, 1번째는 경도
        private Long companyId;

        private List<String> postTags;
        private List<String> availableServiceTags;
    }

    @Getter
    public static class PatchDto {

        private String title;
        private String content;
        private List<String> address;
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
        private List<String> address;
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
