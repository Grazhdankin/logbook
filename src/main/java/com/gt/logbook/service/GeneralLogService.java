package com.gt.logbook.service;

import java.util.List;
import java.util.Optional;

import com.gt.logbook.domain.entity.GeneralLog;

public interface GeneralLogService {

    List<GeneralLog> findAll();

    Optional<GeneralLog> findOne(Long id);

    List<GeneralLog> findAllRevisions(Long id);

    GeneralLog save(GeneralLog entity);

    void delete(Long id);
}
