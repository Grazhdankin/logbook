package com.gt.logbook.service;

import com.gt.logbook.domain.entity.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Flux<User> findAll();

    Mono<User> findOne(Long id);

    Mono<User> findByUsername(String username);

    Flux<User> findAllRevisions(Long id);

    Mono<User> save(User entity);

    Mono<Void> delete(Long id);
}