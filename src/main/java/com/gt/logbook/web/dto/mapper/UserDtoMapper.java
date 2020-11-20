package com.gt.logbook.web.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.gt.logbook.domain.entity.User;
import com.gt.logbook.web.dto.UserDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {

    @Mapping(ignore = true, target = "password")
    UserDto toDto(User entity);

    List<UserDto> toDto(List<User> entityList);

    @Mapping(ignore = true, target = "updatedAt")
    User toEntity(UserDto dto);
}