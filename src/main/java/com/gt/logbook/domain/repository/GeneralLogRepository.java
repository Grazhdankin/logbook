package com.gt.logbook.domain.repository;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import com.gt.logbook.domain.entity.GeneralLog;

@Repository
public interface GeneralLogRepository extends R2dbcRepository<GeneralLog, Long>, RevisionRepository<GeneralLog, Long, Integer> {
}