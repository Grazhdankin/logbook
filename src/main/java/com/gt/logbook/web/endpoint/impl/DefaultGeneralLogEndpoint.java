package com.gt.logbook.web.endpoint.impl;

import org.springframework.stereotype.Service;
import com.gt.logbook.service.GeneralLogService;
import com.gt.logbook.web.dto.GeneralLogDto;
import com.gt.logbook.web.dto.mapper.GeneralLogDtoMapper;
import com.gt.logbook.web.endpoint.GeneralLogEndpoint;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultGeneralLogEndpoint implements GeneralLogEndpoint {

    private final GeneralLogService service;
    private final GeneralLogDtoMapper mapper;

    public DefaultGeneralLogEndpoint(GeneralLogService service, GeneralLogDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public Flux<GeneralLogDto> findAll() {
        return service.findAll().map(mapper::toDto);
    }

    @Override
    public Mono<GeneralLogDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public Flux<GeneralLogDto> findAllRevisions(Long id) {
        return service.findAllRevisions(id).map(mapper::toDto);
    }

    @Override
    public Mono<GeneralLogDto> save(GeneralLogDto dto) {
        return service.save(mapper.toEntity(dto)).map(mapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return service.delete(id);
    }
}