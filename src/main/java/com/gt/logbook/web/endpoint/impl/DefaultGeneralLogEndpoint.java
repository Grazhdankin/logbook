package com.gt.logbook.web.endpoint.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.gt.logbook.service.GeneralLogService;
import com.gt.logbook.web.dto.GeneralLogDto;
import com.gt.logbook.web.dto.mapper.GeneralLogDtoMapper;
import com.gt.logbook.web.endpoint.GeneralLogEndpoint;

@Service
public class DefaultGeneralLogEndpoint implements GeneralLogEndpoint {

    private final GeneralLogService service;
    private final GeneralLogDtoMapper mapper;

    public DefaultGeneralLogEndpoint(GeneralLogService service, GeneralLogDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<GeneralLogDto> findAll() {
        return mapper.toDto(service.findAll());
    }

    @Override
    public Optional<GeneralLogDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public GeneralLogDto save(GeneralLogDto dto) {
        return mapper.toDto(service.save(mapper.toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}