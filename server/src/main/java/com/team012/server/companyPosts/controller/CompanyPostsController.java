package com.team012.server.companyPosts.controller;

import com.team012.server.companyEtc.aws.service.AwsS3Service;
import com.team012.server.companyEtc.entity.CompanyPostsImg;
import com.team012.server.companyPosts.dto.CompanyPostsDto;
import com.team012.server.companyPosts.entity.CompanyPosts;
import com.team012.server.companyPosts.mapper.CompanyPostsMapper;
import com.team012.server.companyPosts.service.CompanyPostsService;
import com.team012.server.dto.MultiResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/posts")
@RestController
public class CompanyPostsController {

    private final CompanyPostsService companyPostsService;
    private final AwsS3Service awsS3Service;
    private final CompanyPostsMapper mapper;

    @PostMapping("/create") //@AuthenticationPrincipal PrincipalDetails principal 이거 없으므로 일단 dto에 companyId보냄
    public ResponseEntity create(@RequestPart(value = "request") CompanyPostsDto.PostDto request,
                                 @RequestPart(value = "file") List<MultipartFile> file) {
        Long companyId = request.getCompanyId();
        CompanyPosts companyPosts = mapper.postDtoToCompanyPosts(request);
        List<CompanyPostsImg> imageUrlList = awsS3Service.uploadFilesV2(file);

        CompanyPosts response = companyPostsService.save(companyPosts, companyId, imageUrlList);

        return new ResponseEntity<>(mapper.companyPostsToResponseDto(response), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity update (@PathVariable("id") Long id,
                                  @RequestBody CompanyPostsDto.PatchDto patchDto) {
        Long companyId = patchDto.getCompanyId();

        CompanyPosts companyPosts = mapper.patchDtoToCompanyPosts(patchDto);
        companyPosts.setId(id);
        CompanyPosts response = companyPostsService.update(companyPosts, companyId);

        return new ResponseEntity<>(mapper.companyPostsToResponseDto(response),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {

        CompanyPosts response = companyPostsService.findById(id);

        return new ResponseEntity<>(mapper.companyPostsToResponseDto(response), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity gets(@RequestParam int page,
                               @RequestParam int size) {
        Page<CompanyPosts> companyPostsPage = companyPostsService.findByPage(page+1, size);
        List<CompanyPosts> companyPostsList = companyPostsPage.getContent();

        return new ResponseEntity<>(new MultiResponseDto<>(mapper.companyPostsToResponseDtos(companyPostsList), companyPostsPage), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        companyPostsService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
