package com.gt.logbook.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import com.gt.logbook.domain.entity.TanksLog;

@Repository
public interface TanksLogRepository extends JpaRepository<TanksLog, Long>, RevisionRepository<TanksLog, Long, Integer> {

    List<TanksLog> findByGeneralLog_Id(Long id);
}