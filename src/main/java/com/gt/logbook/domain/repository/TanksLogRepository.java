package com.gt.logbook.domain.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import com.gt.logbook.domain.entity.TanksLog;
import reactor.core.publisher.Flux;

@Repository
public interface TanksLogRepository extends R2dbcRepository<TanksLog, Long>, RevisionRepository<TanksLog, Long, Integer> {

    Flux<TanksLog> findByGeneralLog_Id(Long id);
}