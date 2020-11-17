package com.gt.logbook.web.endpoint;

import java.util.List;
import java.util.Optional;

import com.gt.logbook.web.dto.WeatherLogDto;

public interface WeatherLogEndpoint {

    List<WeatherLogDto> findAll();

    Optional<WeatherLogDto> findOne(Long id);

    WeatherLogDto save(WeatherLogDto dto);

    void delete(Long id);
}
