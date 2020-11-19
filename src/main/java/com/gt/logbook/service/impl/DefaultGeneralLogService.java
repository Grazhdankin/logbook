package com.gt.logbook.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.domain.entity.GeneralLog;
import com.gt.logbook.domain.repository.GeneralLogRepository;
import com.gt.logbook.service.GeneralLogService;

@Service
public class DefaultGeneralLogService implements GeneralLogService {

    private final GeneralLogRepository repository;

    public DefaultGeneralLogService(GeneralLogRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<GeneralLog> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Optional<GeneralLog> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<GeneralLog> findAllRevisions(Long id) {
        return repository.findRevisions(id).reverse().stream().map(Revision::getEntity).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public GeneralLog save(GeneralLog entity) {
        return repository.saveAndFlush(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}