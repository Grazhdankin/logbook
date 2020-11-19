package com.gt.logbook.web.endpoint.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.gt.logbook.service.WeatherLogService;
import com.gt.logbook.web.dto.WeatherLogDto;
import com.gt.logbook.web.dto.mapper.WeatherLogDtoMapper;
import com.gt.logbook.web.endpoint.WeatherLogEndpoint;

@Service
public class DefaultWeatherLogEndpoint implements WeatherLogEndpoint {

    private final WeatherLogService service;
    private final WeatherLogDtoMapper mapper;

    public DefaultWeatherLogEndpoint(WeatherLogService service, WeatherLogDtoMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public List<WeatherLogDto> findAll() {
        return mapper.toDto(service.findAll());
    }

    @Override
    public Optional<WeatherLogDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public List<WeatherLogDto> findAllRevisions(Long id) {
        return mapper.toDto(service.findAllRevisions(id));
    }

    @Override
    public WeatherLogDto save(WeatherLogDto dto) {
        return mapper.toDto(service.save(mapper.toEntity(dto)));
    }

    @Override
    public void delete(Long id) {
        service.delete(id);
    }
}
