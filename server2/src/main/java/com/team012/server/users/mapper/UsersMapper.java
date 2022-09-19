package com.team012.server.users.mapper;

import com.team012.server.users.dto.UsersDto;
import com.team012.server.users.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsersMapper {
    Users companyDtoPostToCompany(UsersDto.Post dto);
    UsersDto.Response companyToCompanyDtoResponse(Users company);
}
