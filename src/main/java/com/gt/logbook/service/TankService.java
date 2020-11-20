package com.gt.logbook.service;

import com.gt.logbook.domain.entity.Tank;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TankService {

    Flux<Tank> findAll();

    Mono<Tank> findOne(Long id);

    Flux<Tank> findAllRevisions(Long id);

    Mono<Tank> save(Tank entity);

    Mono<Void> delete(Long id);
}