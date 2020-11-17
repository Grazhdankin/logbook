package com.gt.logbook.service;

import java.util.List;
import java.util.Optional;

import com.gt.logbook.domain.entity.TanksLog;

public interface TanksLogService {

    List<TanksLog> findAll();

    Optional<TanksLog> findOne(Long id);

    TanksLog save(TanksLog entity);

    void delete(Long id);
}