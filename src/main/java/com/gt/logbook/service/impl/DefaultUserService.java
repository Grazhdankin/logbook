package com.gt.logbook.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.domain.entity.User;
import com.gt.logbook.domain.repository.UserRepository;
import com.gt.logbook.service.UserService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultUserService implements UserService {

    private final UserRepository repository;

    public DefaultUserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Flux<User> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Mono<User> findOne(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Mono<User> findByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Transactional
    @Override
    public Flux<User> findAllRevisions(Long id) {
        return Flux.defer(() -> Flux.fromIterable(repository.findRevisions(id).reverse().stream().map(Revision::getEntity).collect(Collectors.toList())));
    }

    @Transactional
    @Override
    public Mono<User> save(User entity) {
        return repository.save(entity);
    }

    @Transactional
    @Override
    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }
}