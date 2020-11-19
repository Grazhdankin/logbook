package com.gt.logbook.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.domain.entity.TanksLog;
import com.gt.logbook.domain.repository.TanksLogRepository;
import com.gt.logbook.service.TanksLogService;

@Service
public class DefaultTanksLogService implements TanksLogService {

    private final TanksLogRepository repository;

    public DefaultTanksLogService(TanksLogRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<TanksLog> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Optional<TanksLog> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<TanksLog> findAllRevisions(Long id) {
        return repository.findRevisions(id).reverse().stream().map(Revision::getEntity).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public TanksLog save(TanksLog entity) {
        return repository.saveAndFlush(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}