package com.gt.logbook.web.endpoint.impl;

import org.springframework.stereotype.Service;
import com.gt.logbook.service.TankService;
import com.gt.logbook.service.UserService;
import com.gt.logbook.web.dto.TankDto;
import com.gt.logbook.web.dto.mapper.TankDtoMapper;
import com.gt.logbook.web.endpoint.TankEndpoint;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultTankEndpoint implements TankEndpoint {

    private final TankService service;
    private final TankDtoMapper mapper;
    private final UserService userService;

    public DefaultTankEndpoint(TankService service,
                               TankDtoMapper mapper,
                               UserService userService) {
        this.service = service;
        this.mapper = mapper;
        this.userService = userService;
    }

    @Override
    public Flux<TankDto> findAll() {
        return service.findAll().map(mapper::toDto);
    }

    @Override
    public Mono<TankDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public Flux<TankDto> findAllRevisions(Long id) {
        return service.findAllRevisions(id).map(mapper::toDto);
    }

    @Override
    public Mono<TankDto> save(TankDto dto) {
        return service.save(mapper.toEntity(dto)).map(mapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return service.delete(id);
    }
}