package com.gt.logbook.web.endpoint;

import java.util.List;
import java.util.Optional;

import com.gt.logbook.web.dto.PassageLogDto;

public interface PassageLogEndpoint {

    List<PassageLogDto> findAll();

    Optional<PassageLogDto> findOne(Long id);

    List<PassageLogDto> findByGeneralLogId(Long id);

    List<PassageLogDto> findAllRevisions(Long id);

    PassageLogDto save(PassageLogDto dto);

    void delete(Long id);
}
