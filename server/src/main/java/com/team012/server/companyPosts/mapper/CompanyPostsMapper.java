package com.team012.server.companyPosts.mapper;

import com.team012.server.address.Address;
import com.team012.server.companyPosts.Tag.AvaliableServiceTags.entity.PostAvailableTags;
import com.team012.server.companyPosts.Tag.PostsTag.entity.CompanyPostsTags;
import com.team012.server.companyPosts.dto.CompanyPostsDto;
import com.team012.server.companyPosts.entity.CompanyPosts;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CompanyPostsMapper {

    @Mapping(target = "address", ignore = true)
    CompanyPosts postDtoToCompanyPosts(CompanyPostsDto.PostDto postDto);

    @Mapping(target = "address", ignore = true)
    CompanyPosts patchDtoToCompanyPosts(CompanyPostsDto.PatchDto patchDto);

    default CompanyPostsDto.ResponseDto companyPostsToResponseDto(CompanyPosts companyPosts) {
        Address addr = companyPosts.getAddress();
        List<String> address = List.of(addr.getLatitude(), addr.getLongitude());

        CompanyPostsDto.ResponseDto responseDto = CompanyPostsDto.ResponseDto.builder()
                .id(companyPosts.getId())
                .companyId(companyPosts.getCompany().getId())
                .title(companyPosts.getTitle())
                .content(companyPosts.getContent())
                .address(address)
                .companyPostsImgList(companyPosts.getCompanyPostsImgList())
                .build();

        if(companyPosts.getCompanyPostsTags().size() != 0) {
            List<CompanyPostsTags> list = companyPosts.getCompanyPostsTags();
            List<String> pt = new ArrayList<>();
            for(CompanyPostsTags c : list) {
                String tags = c.getPostsTags().getTag();
                pt.add(tags);
            }
            responseDto.setPostTags(pt);
        }

        if(companyPosts.getPostAvailableTags().size() != 0) {
            List<PostAvailableTags> list = companyPosts.getPostAvailableTags();
            List<String> ast = new ArrayList<>();
            for(PostAvailableTags c : list) {
                String tags = c.getAvailableServiceTags().getTag();
                ast.add(tags);
            }
            responseDto.setAvailableServiceTags(ast);
        }

        return responseDto;
    }

    List<CompanyPostsDto.ResponseDto> companyPostsToResponseDtos(List<CompanyPosts> companyPostsList);
}
