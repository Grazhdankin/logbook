package com.gt.logbook.web.endpoint.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.gt.logbook.service.TanksLogService;
import com.gt.logbook.web.dto.TanksLogDto;
import com.gt.logbook.web.dto.mapper.TanksLogDtoMapper;
import com.gt.logbook.web.endpoint.TanksLogEndpoint;

@Service
public class DefaultTanksLogEndpoint implements TanksLogEndpoint {

    private final TanksLogService service;
    private final TanksLogDtoMapper mapper;

    public DefaultTanksLogEndpoint(TanksLogService service, TanksLogDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<TanksLogDto> findAll() {
        return mapper.toDto(service.findAll());
    }

    @Override
    public Optional<TanksLogDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public List<TanksLogDto> findAllRevisions(Long id) {
        return mapper.toDto(service.findAllRevisions(id));
    }

    @Override
    public TanksLogDto save(TanksLogDto dto) {
        return mapper.toDto(service.save(mapper.toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}