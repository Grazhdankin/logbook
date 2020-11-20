package com.gt.logbook.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.history.Revision;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gt.logbook.domain.entity.WeatherLog;
import com.gt.logbook.domain.repository.WeatherLogRepository;
import com.gt.logbook.service.WeatherLogService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DefaultWeatherLogService implements WeatherLogService {

    private final WeatherLogRepository repository;

    public DefaultWeatherLogService(WeatherLogRepository repository) {
        this.repository = repository;
    }

    @Transactional
    @Override
    public Flux<WeatherLog> findAll() {
        return repository.findAll();
    }

    @Transactional
    @Override
    public Mono<WeatherLog> findOne(Long id) {
        return repository.findById(id);
    }

    @Override
    public Flux<WeatherLog> findByGeneralLogId(Long id) {
        return repository.findByGeneralLog_Id(id);
    }

    @Transactional
    @Override
    public Flux<WeatherLog> findAllRevisions(Long id) {
        return Flux.defer(() -> Flux.fromIterable(repository.findRevisions(id).reverse().stream().map(Revision::getEntity).collect(Collectors.toList())));
    }

    @Transactional
    @Override
    public Mono<WeatherLog> save(WeatherLog entity) {
        return repository.save(entity);
    }

    @Transactional
    @Override
    public Mono<Void> delete(Long id) {
        return repository.deleteById(id);
    }
}