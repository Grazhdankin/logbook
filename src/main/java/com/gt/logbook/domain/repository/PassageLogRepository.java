package com.gt.logbook.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.gt.logbook.domain.entity.PassageLog;

@Repository
public interface PassageLogRepository extends JpaRepository<PassageLog, Long> {
}