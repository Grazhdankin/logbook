package com.gt.logbook.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.domain.entity.PassageLog;
import com.gt.logbook.domain.repository.PassageLogRepository;
import com.gt.logbook.service.PassageLogService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultPassageLogService implements PassageLogService {

    private final PassageLogRepository repository;

    public DefaultPassageLogService(PassageLogRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Flux<PassageLog> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Mono<PassageLog> findOne(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Flux<PassageLog> findByGeneralLogId(Long id) {
        return repository.findByGeneralLog_Id(id);
    }

    @Transactional
    @Override
    public Flux<PassageLog> findAllRevisions(Long id) {
        return Flux.defer(() -> Flux.fromIterable(repository.findRevisions(id).reverse().stream().map(Revision::getEntity).collect(Collectors.toList())));
    }

    @Transactional
    @Override
    public Mono<PassageLog> save(PassageLog entity) {
        return repository.save(entity);
    }

    @Transactional
    @Override
    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }
}