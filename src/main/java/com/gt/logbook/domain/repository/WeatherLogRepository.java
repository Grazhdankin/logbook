package com.gt.logbook.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;
import org.springframework.stereotype.Repository;
import com.gt.logbook.domain.entity.WeatherLog;

@Repository
public interface WeatherLogRepository extends JpaRepository<WeatherLog, Long>, RevisionRepository<WeatherLog, Long, Integer> {

    List<WeatherLog> findByGeneralLog_Id(Long id);
}