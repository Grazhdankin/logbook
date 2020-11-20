package com.gt.logbook.domain.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import com.gt.logbook.domain.entity.CommonLog;
import reactor.core.publisher.Flux;

@Repository
public interface CommonLogRepository extends R2dbcRepository<CommonLog, Long>, RevisionRepository<CommonLog, Long, Integer> {

    Flux<CommonLog> findByGeneralLog_Id(Long id);
}