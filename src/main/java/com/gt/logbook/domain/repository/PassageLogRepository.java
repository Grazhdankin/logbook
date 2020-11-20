package com.gt.logbook.domain.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import com.gt.logbook.domain.entity.PassageLog;
import reactor.core.publisher.Flux;

@Repository
public interface PassageLogRepository extends R2dbcRepository<PassageLog, Long>, RevisionRepository<PassageLog, Long, Integer> {

    Flux<PassageLog> findByGeneralLog_Id(Long id);
}