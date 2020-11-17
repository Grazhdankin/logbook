package com.gt.logbook.web.endpoint;

import java.util.List;
import java.util.Optional;

import com.gt.logbook.web.dto.CommonLogDto;

public interface CommonLogEndpoint {

    List<CommonLogDto> findAll();

    Optional<CommonLogDto> findOne(Long id);

    CommonLogDto save(CommonLogDto dto);

    void delete(Long id);
}