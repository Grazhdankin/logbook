package com.gt.logbook.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.domain.entity.PassageLog;
import com.gt.logbook.domain.repository.PassageLogRepository;
import com.gt.logbook.service.PassageLogService;

@Service
public class DefaultPassageLogService implements PassageLogService {

    private final PassageLogRepository repository;

    public DefaultPassageLogService(PassageLogRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<PassageLog> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Optional<PassageLog> findOne(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public List<PassageLog> findByGeneralLogId(Long id) {
        return repository.findByGeneralLog_Id(id);
    }

    @Transactional
    @Override
    public List<PassageLog> findAllRevisions(Long id) {
        return repository.findRevisions(id).reverse().stream().map(Revision::getEntity).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public PassageLog save(PassageLog entity) {
        return repository.saveAndFlush(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}