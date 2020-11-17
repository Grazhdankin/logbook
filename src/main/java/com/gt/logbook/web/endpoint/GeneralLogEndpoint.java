package com.gt.logbook.web.endpoint;

import java.util.List;
import java.util.Optional;

import com.gt.logbook.web.dto.GeneralLogDto;

public interface GeneralLogEndpoint {

    List<GeneralLogDto> findAll();

    Optional<GeneralLogDto> findOne(Long id);

    GeneralLogDto save(GeneralLogDto dto);

    void delete(Long id);
}