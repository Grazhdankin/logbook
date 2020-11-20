package com.gt.logbook.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.domain.entity.TanksLog;
import com.gt.logbook.domain.repository.TanksLogRepository;
import com.gt.logbook.service.TanksLogService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultTanksLogService implements TanksLogService {

    private final TanksLogRepository repository;

    public DefaultTanksLogService(TanksLogRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Flux<TanksLog> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Mono<TanksLog> findOne(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Flux<TanksLog> findByGeneralLogId(Long id) {
        return repository.findByGeneralLog_Id(id);
    }

    @Transactional
    @Override
    public Flux<TanksLog> findAllRevisions(Long id) {
        return Flux.defer(() -> Flux.fromIterable(repository.findRevisions(id).reverse().stream().map(Revision::getEntity).collect(Collectors.toList())));
    }

    @Transactional
    @Override
    public Mono<TanksLog> save(TanksLog entity) {
        return repository.save(entity);
    }

    @Transactional
    @Override
    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }
}