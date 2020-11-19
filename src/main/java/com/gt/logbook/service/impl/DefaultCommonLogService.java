package com.gt.logbook.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.domain.entity.CommonLog;
import com.gt.logbook.domain.repository.CommonLogRepository;
import com.gt.logbook.service.CommonLogService;

@Service
public class DefaultCommonLogService implements CommonLogService {

    private final CommonLogRepository repository;

    public DefaultCommonLogService(CommonLogRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<CommonLog> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Optional<CommonLog> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<CommonLog> findAllRevisions(Long id) {
        return repository.findRevisions(id).reverse().stream().map(Revision::getEntity).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CommonLog save(CommonLog entity) {
        return repository.saveAndFlush(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
