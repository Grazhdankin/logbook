package com.gt.logbook.web.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.gt.logbook.domain.entity.TanksLog;
import com.gt.logbook.web.dto.TanksLogDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {GeneralLogDtoMapper.class, TankDtoMapper.class})
public interface TanksLogDtoMapper {

    @Mapping(source="generalLog.id", target="generalLogId")
    TanksLogDto toDto(TanksLog entity);

    List<TanksLogDto> toDto(List<TanksLog> entityList);

    @Mapping(source = "generalLogId", target = "generalLog")
    TanksLog toEntity(TanksLogDto dto);
}