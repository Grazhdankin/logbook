package com.gt.logbook.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.domain.entity.GeneralLog;
import com.gt.logbook.domain.repository.GeneralLogRepository;
import com.gt.logbook.service.GeneralLogService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultGeneralLogService implements GeneralLogService {

    private final GeneralLogRepository repository;

    public DefaultGeneralLogService(GeneralLogRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Flux<GeneralLog> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Mono<GeneralLog> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public Flux<GeneralLog> findAllRevisions(Long id) {
        return Flux.defer(() -> Flux.fromIterable(repository.findRevisions(id).reverse().stream().map(Revision::getEntity).collect(Collectors.toList())));
    }

    @Transactional
    @Override
    public Mono<GeneralLog> save(GeneralLog entity) {
        return repository.save(entity);
    }

    @Transactional
    @Override
    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }
}