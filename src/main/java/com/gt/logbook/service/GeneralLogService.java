package com.gt.logbook.service;

import com.gt.logbook.domain.entity.GeneralLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GeneralLogService {

    Flux<GeneralLog> findAll();

    Mono<GeneralLog> findOne(Long id);

    Flux<GeneralLog> findAllRevisions(Long id);

    Mono<GeneralLog> save(GeneralLog entity);

    Mono<Void> delete(Long id);
}
