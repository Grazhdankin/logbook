package com.gt.logbook.web.endpoint;

import java.util.List;
import java.util.Optional;

import com.gt.logbook.web.dto.TankDto;

public interface TankEndpoint {

    List<TankDto> findAll();

    Optional<TankDto> findOne(Long id);

    TankDto save(TankDto dto);

    void delete(Long id);
}