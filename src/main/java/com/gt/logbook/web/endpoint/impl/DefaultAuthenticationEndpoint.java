package com.gt.logbook.web.endpoint.impl;

import org.springframework.stereotype.Service;
import com.gt.logbook.domain.entity.User;
import com.gt.logbook.service.UserService;
import com.gt.logbook.web.endpoint.AuthenticationEndpoint;
import reactor.core.publisher.Mono;

@Service
public class DefaultAuthenticationEndpoint implements AuthenticationEndpoint {

    private final UserService userService;

    public DefaultAuthenticationEndpoint(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Mono<User> findByUsername(String username) {
        return userService.findByUsername(username);
    }
}
