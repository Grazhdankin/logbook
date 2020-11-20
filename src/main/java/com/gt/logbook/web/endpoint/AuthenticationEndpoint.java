package com.gt.logbook.web.endpoint;

import com.gt.logbook.domain.entity.User;
import reactor.core.publisher.Mono;

public interface AuthenticationEndpoint {

    Mono<User> findByUsername(String username);
}
