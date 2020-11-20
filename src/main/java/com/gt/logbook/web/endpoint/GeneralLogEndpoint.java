package com.gt.logbook.web.endpoint;

import com.gt.logbook.web.dto.GeneralLogDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface GeneralLogEndpoint {

    Flux<GeneralLogDto> findAll();

    Mono<GeneralLogDto> findOne(Long id);

    Flux<GeneralLogDto> findAllRevisions(Long id);

    Mono<GeneralLogDto> save(GeneralLogDto dto);

    Mono<Void> delete(Long id);
}