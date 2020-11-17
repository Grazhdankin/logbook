package com.gt.logbook.service;

import java.util.List;
import java.util.Optional;

import com.gt.logbook.domain.entity.PassageLog;

public interface PassageLogService {

    List<PassageLog> findAll();

    Optional<PassageLog> findOne(Long id);

    PassageLog save(PassageLog entity);

    void delete(Long id);
}