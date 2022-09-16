package com.team012.server.companyPosts.mapper;

import com.team012.server.companyPosts.converter.ListToString;
import com.team012.server.companyPosts.dto.CompanyPostsDto;
import com.team012.server.companyPosts.entity.CompanyPosts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyPostsMapper {

    @Mapping(target = "postTags", ignore = true)
    @Mapping(target = "availableServiceTags", ignore = true)
    CompanyPosts postDtoToCompanyPosts(CompanyPostsDto.PostDto postDto);

    @Mapping(target = "postTags", ignore = true)
    @Mapping(target = "availableServiceTags", ignore = true)
    CompanyPosts patchDtoToCompanyPosts(CompanyPostsDto.PatchDto patchDto);

    default CompanyPostsDto.ResponseDto companyPostsToResponseDto(CompanyPosts companyPosts) {
        CompanyPostsDto.ResponseDto responseDto = CompanyPostsDto.ResponseDto.builder()
                .id(companyPosts.getId())
                .companyId(companyPosts.getCompany().getId())
                .title(companyPosts.getTitle())
                .content(companyPosts.getContent())
                .address(companyPosts.getAddress())
                .companyPostsImgList(companyPosts.getCompanyPostsImgList())
                .build();
        if(companyPosts.getPostTags() != null) {
            List<String> postTags = Arrays.asList(companyPosts.getPostTags().split(","));
            responseDto.setPostTags(postTags);
        }
        if(companyPosts.getAvailableServiceTags() != null) {
            List<String> availableServiceTags = Arrays.asList(companyPosts.getAvailableServiceTags().split(","));
            responseDto.setAvailableServiceTags(availableServiceTags);
        }
        return responseDto;
    }

    List<CompanyPostsDto.ResponseDto> companyPostsToResponseDtos(List<CompanyPosts> companyPostsList);
}
