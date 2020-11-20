package com.gt.logbook.web.endpoint;

import com.gt.logbook.web.dto.PassageLogDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PassageLogEndpoint {

    Flux<PassageLogDto> findAll();

    Mono<PassageLogDto> findOne(Long id);

    Flux<PassageLogDto> findByGeneralLogId(Long id);

    Flux<PassageLogDto> findAllRevisions(Long id);

    Mono<PassageLogDto> save(PassageLogDto dto);

    Mono<Void> delete(Long id);
}
