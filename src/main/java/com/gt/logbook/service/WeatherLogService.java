package com.gt.logbook.service;

import java.util.List;
import java.util.Optional;

import com.gt.logbook.domain.entity.WeatherLog;

public interface WeatherLogService {

    List<WeatherLog> findAll();

    Optional<WeatherLog> findOne(Long id);

    List<WeatherLog> findByGeneralLogId(Long id);

    List<WeatherLog> findAllRevisions(Long id);

    WeatherLog save(WeatherLog entity);

    void delete(Long id);
}