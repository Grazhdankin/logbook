package com.gt.logbook.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import com.gt.logbook.domain.entity.CommonLog;

@Repository
public interface CommonLogRepository extends JpaRepository<CommonLog, Long>, RevisionRepository<CommonLog, Long, Integer> {

    List<CommonLog> findByGeneralLog_Id(Long id);
}