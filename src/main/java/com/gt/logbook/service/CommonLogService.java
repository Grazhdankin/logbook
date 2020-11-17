package com.gt.logbook.service;

import java.util.List;
import java.util.Optional;

import com.gt.logbook.domain.entity.CommonLog;

public interface CommonLogService {

    List<CommonLog> findAll();

    Optional<CommonLog> findOne(Long id);

    CommonLog save(CommonLog entity);

    void delete(Long id);
}