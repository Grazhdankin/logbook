package com.gt.logbook.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.domain.entity.WeatherLog;
import com.gt.logbook.domain.repository.WeatherLogRepository;
import com.gt.logbook.service.WeatherLogService;

@Service
public class DefaultWeatherLogService implements WeatherLogService {

    private final WeatherLogRepository repository;

    public DefaultWeatherLogService(WeatherLogRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public List<WeatherLog> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Optional<WeatherLog> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<WeatherLog> findAllRevisions(Long id) {
        return repository.findRevisions(id).reverse().stream().map(Revision::getEntity).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public WeatherLog save(WeatherLog entity) {
        return repository.saveAndFlush(entity);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}