package com.gt.logbook.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.domain.entity.Tank;
import com.gt.logbook.domain.repository.TankRepository;
import com.gt.logbook.service.TankService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultTankService implements TankService {

    private final TankRepository repository;

    public DefaultTankService(TankRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Flux<Tank> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Mono<Tank> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public Flux<Tank> findAllRevisions(Long id) {
        return Flux.defer(() -> Flux.fromIterable(repository.findRevisions(id).reverse().stream().map(Revision::getEntity).collect(Collectors.toList())));
    }

    @Transactional
    @Override
    public Mono<Tank> save(Tank entity) {
        return repository.save(entity);
    }

    @Transactional
    @Override
    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }
}