package com.gt.logbook.web.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.gt.logbook.domain.entity.User;
import com.gt.logbook.web.dto.UserDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserDtoMapper {

    UserDto toDto(User entity);

    List<UserDto> toDto(List<User> entityList);

    User toEntity(UserDto dto);

    default User fromId(final Long id) {
        if (id == null) {
            return null;
        }

        final User user = new User();
        user.setId(id);

        return user;
    }
}