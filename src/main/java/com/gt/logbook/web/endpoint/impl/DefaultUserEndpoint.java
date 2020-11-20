package com.gt.logbook.web.endpoint.impl;

import org.springframework.stereotype.Service;
import com.gt.logbook.security.PBKDF2Encoder;
import com.gt.logbook.service.UserService;
import com.gt.logbook.web.dto.UserDto;
import com.gt.logbook.web.dto.mapper.UserDtoMapper;
import com.gt.logbook.web.endpoint.UserEndpoint;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultUserEndpoint implements UserEndpoint {

    private final UserService service;
    private final UserDtoMapper mapper;
    private final PBKDF2Encoder passwordEncoder;

    public DefaultUserEndpoint(UserService service,
                               UserDtoMapper mapper,
                               PBKDF2Encoder passwordEncoder) {
        this.service = service;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Flux<UserDto> findAll() {
        return service.findAll().map(mapper::toDto);
    }

    @Override
    public Mono<UserDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public Flux<UserDto> findAllRevisions(Long id) {
        return service.findAllRevisions(id).map(mapper::toDto);
    }

    @Override
    public Mono<UserDto> save(UserDto dto) {
        return service.save(mapper.toEntity(dto.toBuilder().password(passwordEncoder.encode(dto.getPassword())).build()))
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return service.delete(id);
    }
}