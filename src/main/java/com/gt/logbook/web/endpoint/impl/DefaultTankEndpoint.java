package com.gt.logbook.web.endpoint.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.gt.logbook.service.TankService;
import com.gt.logbook.web.dto.TankDto;
import com.gt.logbook.web.dto.mapper.TankDtoMapper;
import com.gt.logbook.web.endpoint.TankEndpoint;

@Service
public class DefaultTankEndpoint implements TankEndpoint {

    private final TankService service;
    private final TankDtoMapper mapper;

    public DefaultTankEndpoint(TankService service, TankDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<TankDto> findAll() {
        return mapper.toDto(service.findAll());
    }

    @Override
    public Optional<TankDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public List<TankDto> findAllRevisions(Long id) {
        return mapper.toDto(service.findAllRevisions(id));
    }

    @Override
    public TankDto save(TankDto dto) {
        return mapper.toDto(service.save(mapper.toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}