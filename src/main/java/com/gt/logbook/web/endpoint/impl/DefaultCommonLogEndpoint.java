package com.gt.logbook.web.endpoint.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.gt.logbook.service.CommonLogService;
import com.gt.logbook.web.dto.CommonLogDto;
import com.gt.logbook.web.dto.mapper.CommonLogDtoMapper;
import com.gt.logbook.web.endpoint.CommonLogEndpoint;

@Service
public class DefaultCommonLogEndpoint implements CommonLogEndpoint {

    private final CommonLogService service;
    private final CommonLogDtoMapper mapper;

    public DefaultCommonLogEndpoint(CommonLogService service, CommonLogDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<CommonLogDto> findAll() {
        return mapper.toDto(service.findAll());
    }

    @Override
    public Optional<CommonLogDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public CommonLogDto save(CommonLogDto dto) {
        return mapper.toDto(service.save(mapper.toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}