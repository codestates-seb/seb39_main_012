package com.team012.server.posts.Tag.ServiceTag.controller;

import com.team012.server.posts.Tag.ServiceTag.dto.ServiceResponseDto;
import com.team012.server.posts.Tag.ServiceTag.dto.ServiceTagUpdateDto;
import com.team012.server.posts.Tag.ServiceTag.entity.ServiceTag;
import com.team012.server.posts.Tag.ServiceTag.service.ServiceTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/company/serviceTag")
public class ServiceTagController {

    private final ServiceTagService serviceTagService;

    @PatchMapping("/{servicetag-id}")
    public ResponseEntity updateServiceTag(@PathVariable("servicetag-id") Long serviceTagId,
                                           @RequestBody ServiceTagUpdateDto serviceTagUpdateDto) {
        serviceTagUpdateDto.setServiceTagId(serviceTagId);
        ServiceTag serviceTag = serviceTagService.updateServiceTag(serviceTagUpdateDto);

        ServiceResponseDto responseDto = ServiceResponseDto.builder()
                .serviceTagId(serviceTag.getId())
                .tag(serviceTag.getTag())
                .build();

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{servicetag-id}")
    public ResponseEntity updateServiceTag(@PathVariable("servicetag-id") Long serviceTagId) {
        serviceTagService.deleteServiceTag(serviceTagId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
