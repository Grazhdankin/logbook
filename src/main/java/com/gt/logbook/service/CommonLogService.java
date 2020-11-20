package com.gt.logbook.service;

import com.gt.logbook.domain.entity.CommonLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommonLogService {

    Flux<CommonLog> findAll();

    Mono<CommonLog> findOne(Long id);

    Flux<CommonLog> findByGeneralLogId(Long id);

    Flux<CommonLog> findAllRevisions(Long id);

    Mono<CommonLog> save(CommonLog entity);

    Mono<Void> delete(Long id);
}