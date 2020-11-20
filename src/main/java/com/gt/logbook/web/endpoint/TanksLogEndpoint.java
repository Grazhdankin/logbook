package com.gt.logbook.web.endpoint;

import com.gt.logbook.web.dto.TanksLogDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TanksLogEndpoint {

    Flux<TanksLogDto> findAll();

    Mono<TanksLogDto> findOne(Long id);

    Flux<TanksLogDto> findByGeneralLogId(Long id);

    Flux<TanksLogDto> findAllRevisions(Long id);

    Mono<TanksLogDto> save(TanksLogDto dto);

    Mono<Void> delete(Long id);
}