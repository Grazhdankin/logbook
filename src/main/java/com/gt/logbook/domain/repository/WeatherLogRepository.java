package com.gt.logbook.domain.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import com.gt.logbook.domain.entity.WeatherLog;
import reactor.core.publisher.Flux;

@Repository
public interface WeatherLogRepository extends R2dbcRepository<WeatherLog, Long>, RevisionRepository<WeatherLog, Long, Integer> {

    Flux<WeatherLog> findByGeneralLog_Id(Long id);
}