package com.gt.logbook.web.endpoint.impl;

import org.springframework.stereotype.Service;
import com.gt.logbook.service.GeneralLogService;
import com.gt.logbook.service.WeatherLogService;
import com.gt.logbook.web.dto.WeatherLogDto;
import com.gt.logbook.web.dto.mapper.WeatherLogDtoMapper;
import com.gt.logbook.web.endpoint.WeatherLogEndpoint;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultWeatherLogEndpoint implements WeatherLogEndpoint {

    private final WeatherLogService service;
    private final WeatherLogDtoMapper mapper;
    private final GeneralLogService generalLogService;

    public DefaultWeatherLogEndpoint(WeatherLogService service,
                                     WeatherLogDtoMapper mapper,
                                     GeneralLogService generalLogService) {
        this.service = service;
        this.mapper = mapper;
        this.generalLogService = generalLogService;
    }

    @Override
    public Flux<WeatherLogDto> findAll() {
        return service.findAll().map(mapper::toDto);
    }

    @Override
    public Mono<WeatherLogDto> findOne(Long id) {
        return service.findOne(id).map(mapper::toDto);
    }

    @Override
    public Flux<WeatherLogDto> findByGeneralLogId(Long id) {
        return service.findByGeneralLogId(id).map(mapper::toDto);
    }

    @Override
    public Flux<WeatherLogDto> findAllRevisions(Long id) {
        return service.findAllRevisions(id).map(mapper::toDto);
    }

    @Override
    public Mono<WeatherLogDto> save(WeatherLogDto dto) {
        return generalLogService.findOne(dto.getGeneralLogId())
                .switchIfEmpty(Mono.error(new IllegalArgumentException("Invalid generalLogId '" + dto.getGeneralLogId() + "'!")))
                .map(generalLog -> mapper.toEntity(dto).setGeneralLog(generalLog))
                .flatMap(service::save)
                .map(mapper::toDto);
    }

    @Override
    public Mono<Void> delete(Long id) {
        return service.delete(id);
    }
}
