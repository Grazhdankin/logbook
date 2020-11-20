package com.gt.logbook.web.endpoint;

import com.gt.logbook.web.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserEndpoint {

    Flux<UserDto> findAll();

    Mono<UserDto> findOne(Long id);

    Flux<UserDto> findAllRevisions(Long id);

    Mono<UserDto> save(UserDto dto);

    Mono<Void> delete(Long id);
}