package com.gt.logbook.service;

import com.gt.logbook.domain.entity.TanksLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TanksLogService {

    Flux<TanksLog> findAll();

    Mono<TanksLog> findOne(Long id);

    Flux<TanksLog> findByGeneralLogId(Long id);

    Flux<TanksLog> findAllRevisions(Long id);

    Mono<TanksLog> save(TanksLog entity);

    Mono<Void> delete(Long id);
}