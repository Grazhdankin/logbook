package com.gt.logbook.service;

import java.util.List;
import java.util.Optional;

import com.gt.logbook.domain.entity.Tank;

public interface TankService {

    List<Tank> findAll();

    Optional<Tank> findOne(Long id);

    Tank save(Tank entity);

    void delete(Long id);
}