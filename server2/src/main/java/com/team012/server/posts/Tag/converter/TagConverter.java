package com.team012.server.posts.Tag.converter;

import com.team012.server.posts.Tag.HashTag.dto.HashTagResponseDto;
import com.team012.server.posts.Tag.HashTag.entity.PostsHashTags;
import com.team012.server.posts.Tag.ServiceTag.dto.ServiceResponseDto;
import com.team012.server.posts.Tag.ServiceTag.entity.PostsServiceTag;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagConverter {

    public List<String> toStringList1(List<PostsHashTags> list) {
        List<String> pt = new ArrayList<>();
        for (PostsHashTags c : list) {
            String tags = c.getHashTag().getTag();
            pt.add(tags);
        }
        return pt;
    }
    public List<String> toStringList2(List<PostsServiceTag> list) {
        List<String> ast = new ArrayList<>();
        for (PostsServiceTag c : list) {
            String tags = c.getServiceTag().getTag();
            ast.add(tags);
        }
        return ast;
    }

    public List<HashTagResponseDto> toHashTagDto(List<PostsHashTags> list) {
        List<HashTagResponseDto> list1 = new ArrayList<>();
        for(PostsHashTags p : list) {
            HashTagResponseDto hashTagResponseDto = HashTagResponseDto.builder()
                    .hashTagId(p.getHashTag().getId())
                    .tag(p.getHashTag().getTag())
                    .build();
            list1.add(hashTagResponseDto);
        }
        return list1;
    }

    public List<ServiceResponseDto> toServiceTagDto(List<PostsServiceTag> list) {
        List<ServiceResponseDto> list1 = new ArrayList<>();
        for(PostsServiceTag s : list) {
            ServiceResponseDto serviceResponseDto = ServiceResponseDto.builder()
                    .serviceTagId(s.getServiceTag().getId())
                    .tag(s.getServiceTag().getTag())
                    .build();
            list1.add(serviceResponseDto);
        }
        return list1;
    }




}
