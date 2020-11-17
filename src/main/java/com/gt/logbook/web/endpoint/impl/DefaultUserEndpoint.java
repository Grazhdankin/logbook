package com.gt.logbook.web.endpoint.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.gt.logbook.service.UserService;
import com.gt.logbook.web.dto.UserDto;
import com.gt.logbook.web.dto.mapper.UserDtoMapper;
import com.gt.logbook.web.endpoint.UserEndpoint;

@Service
public class DefaultUserEndpoint implements UserEndpoint {

    private final UserService service;
    private final UserDtoMapper mapper;

    public DefaultUserEndpoint(UserService service, UserDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<UserDto> findAll() {
        return mapper.toDto(service.findAll());
    }

    @Override
    public Optional<UserDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public UserDto save(UserDto dto) {
        return mapper.toDto(service.save(mapper.toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}