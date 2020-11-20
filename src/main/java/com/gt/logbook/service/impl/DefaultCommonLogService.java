package com.gt.logbook.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.domain.entity.CommonLog;
import com.gt.logbook.domain.repository.CommonLogRepository;
import com.gt.logbook.service.CommonLogService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultCommonLogService implements CommonLogService {

    private final CommonLogRepository repository;

    public DefaultCommonLogService(CommonLogRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Flux<CommonLog> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Mono<CommonLog> findOne(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Flux<CommonLog> findByGeneralLogId(Long id) {
        return repository.findByGeneralLog_Id(id);
    }

    @Transactional
    @Override
    public Flux<CommonLog> findAllRevisions(Long id) {
        return Flux.defer(() -> Flux.fromIterable(repository.findRevisions(id).reverse().stream().map(Revision::getEntity).collect(Collectors.toList())));
    }

    @Transactional
    @Override
    public Mono<CommonLog> save(CommonLog entity) {
        return repository.save(entity);
    }

    @Transactional
    @Override
    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }
}
