package com.gt.logbook.web.endpoint;

import java.util.List;
import java.util.Optional;

import com.gt.logbook.web.dto.TanksLogDto;

public interface TanksLogEndpoint {

    List<TanksLogDto> findAll();

    Optional<TanksLogDto> findOne(Long id);

    List<TanksLogDto> findAllRevisions(Long id);

    TanksLogDto save(TanksLogDto dto);

    void delete(Long id);
}