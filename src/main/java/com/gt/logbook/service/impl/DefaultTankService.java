package com.gt.logbook.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.domain.entity.Tank;
import com.gt.logbook.domain.repository.TankRepository;
import com.gt.logbook.service.TankService;

@Service
public class DefaultTankService implements TankService {

    private final TankRepository repository;

    public DefaultTankService(TankRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<Tank> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Optional<Tank> findOne(Long id) {
        return repository.findById(id);
    }

    @Transactional
    @Override
    public Tank save(Tank entity) {
        return repository.saveAndFlush(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}