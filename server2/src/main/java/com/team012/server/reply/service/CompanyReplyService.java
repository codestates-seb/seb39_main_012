package com.team012.server.reply.service;

import com.team012.server.reply.dto.CompanyReplyDto;
import com.team012.server.reply.entity.CompanyReply;
import com.team012.server.reply.repository.CompanyReplyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyReplyService {

    private final CompanyReplyRepository companyReplyRepository;

    public CompanyReplyService(CompanyReplyRepository companyReplyRepository) {
        this.companyReplyRepository = companyReplyRepository;
    }

    public CompanyReply createReply(CompanyReplyDto.Post dto) {
        return null;
    }
}
