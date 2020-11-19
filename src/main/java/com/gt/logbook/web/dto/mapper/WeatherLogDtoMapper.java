package com.gt.logbook.web.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.gt.logbook.domain.entity.WeatherLog;
import com.gt.logbook.web.dto.WeatherLogDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {GeneralLogDtoMapper.class})
public interface WeatherLogDtoMapper {

    @Mapping(source = "generalLog.id", target = "generalLogId")
    WeatherLogDto toDto(WeatherLog entity);

    List<WeatherLogDto> toDto(List<WeatherLog> entityList);

    @Mapping(ignore = true, target = "updatedAt")
    WeatherLog toEntity(WeatherLogDto dto);
}
