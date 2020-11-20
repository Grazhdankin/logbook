package com.gt.logbook.web.endpoint;

import com.gt.logbook.web.dto.CommonLogDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CommonLogEndpoint {

    Flux<CommonLogDto> findAll();

    Mono<CommonLogDto> findOne(Long id);

    Flux<CommonLogDto> findByGeneralLogId(Long id);

    Flux<CommonLogDto> findAllRevisions(Long id);

    Mono<CommonLogDto> save(CommonLogDto dto);

    Mono<Void> delete(Long id);
}