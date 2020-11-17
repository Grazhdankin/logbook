package com.gt.logbook.web.endpoint.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.gt.logbook.service.PassageLogService;
import com.gt.logbook.web.dto.PassageLogDto;
import com.gt.logbook.web.dto.mapper.PassageLogDtoMapper;
import com.gt.logbook.web.endpoint.PassageLogEndpoint;

@Service
public class DefaultPassageLogEndpoint implements PassageLogEndpoint {

    private final PassageLogService service;
    private final PassageLogDtoMapper mapper;

    public DefaultPassageLogEndpoint(PassageLogService service, PassageLogDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<PassageLogDto> findAll() {
        return mapper.toDto(service.findAll());
    }

    @Override
    public Optional<PassageLogDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public PassageLogDto save(PassageLogDto dto) {
        return mapper.toDto(service.save(mapper.toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}