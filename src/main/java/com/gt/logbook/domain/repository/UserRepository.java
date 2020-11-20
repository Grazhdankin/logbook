package com.gt.logbook.domain.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import com.gt.logbook.domain.entity.User;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends R2dbcRepository<User, Long>, RevisionRepository<User, Long, Integer> {

    Mono<User> findByUsername(String username);
}