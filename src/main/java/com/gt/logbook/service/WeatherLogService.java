package com.gt.logbook.service;

import com.gt.logbook.domain.entity.WeatherLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WeatherLogService {

    Flux<WeatherLog> findAll();

    Mono<WeatherLog> findOne(Long id);

    Flux<WeatherLog> findByGeneralLogId(Long id);

    Flux<WeatherLog> findAllRevisions(Long id);

    Mono<WeatherLog> save(WeatherLog entity);

    Mono<Void> delete(Long id);
}