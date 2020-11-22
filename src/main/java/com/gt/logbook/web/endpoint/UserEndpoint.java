package com.gt.logbook.web.endpoint;

import java.util.List;
import java.util.Optional;

import com.gt.logbook.web.dto.UserDto;

public interface UserEndpoint {

    List<UserDto> findAll();

    Optional<UserDto> findOne(Long id);

    UserDto save(UserDto dto);

    void delete(Long id);
}