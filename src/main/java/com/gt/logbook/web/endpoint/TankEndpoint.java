package com.gt.logbook.web.endpoint;

import com.gt.logbook.web.dto.TankDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TankEndpoint {

    Flux<TankDto> findAll();

    Mono<TankDto> findOne(Long id);

    Flux<TankDto> findAllRevisions(Long id);

    Mono<TankDto> save(TankDto dto);

    Mono<Void> delete(Long id);
}