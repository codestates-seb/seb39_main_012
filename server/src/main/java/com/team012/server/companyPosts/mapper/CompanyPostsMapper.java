package com.team012.server.companyPosts.mapper;

import com.team012.server.companyPosts.dto.CompanyPostsDto;
import com.team012.server.companyPosts.entity.CompanyPosts;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyPostsMapper {
    CompanyPosts postDtoToCompanyPosts(CompanyPostsDto.PostDto postDto);

    CompanyPosts patchDtoToCompanyPosts(CompanyPostsDto.PatchDto patchDto);

    CompanyPostsDto.ResponseDto companyPostsToResponseDto(CompanyPosts companyPosts);

    List<CompanyPostsDto.ResponseDto> companyPostsToResponseDtos(List<CompanyPosts> companyPostsList);
}
