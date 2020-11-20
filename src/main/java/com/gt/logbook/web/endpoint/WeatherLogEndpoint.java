package com.gt.logbook.web.endpoint;

import com.gt.logbook.web.dto.WeatherLogDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WeatherLogEndpoint {

    Flux<WeatherLogDto> findAll();

    Mono<WeatherLogDto> findOne(Long id);

    Flux<WeatherLogDto> findByGeneralLogId(Long id);

    Flux<WeatherLogDto> findAllRevisions(Long id);

    Mono<WeatherLogDto> save(WeatherLogDto dto);

    Mono<Void> delete(Long id);
}
