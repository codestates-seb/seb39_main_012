package com.team012.server.company.reply.service;

import com.team012.server.company.reply.entity.CompanyReply;
import com.team012.server.company.reply.repository.CompanyReplyRepository;
import com.team012.server.company.reply.dto.CompanyReplyDto;
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
