package com.gt.logbook.service;

import com.gt.logbook.domain.entity.PassageLog;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PassageLogService {

    Flux<PassageLog> findAll();

    Mono<PassageLog> findOne(Long id);

    Flux<PassageLog> findByGeneralLogId(Long id);

    Flux<PassageLog> findAllRevisions(Long id);

    Mono<PassageLog> save(PassageLog entity);

    Mono<Void> delete(Long id);
}