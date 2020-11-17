package com.gt.logbook.web.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.gt.logbook.domain.entity.CommonLog;
import com.gt.logbook.web.dto.CommonLogDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {GeneralLogDtoMapper.class})
public interface CommonLogDtoMapper {

    @Mapping(source="generalLog.id", target="generalLogId")
    CommonLogDto toDto(CommonLog entity);

    List<CommonLogDto> toDto(List<CommonLog> entityList);

    @Mapping(source = "generalLogId", target = "generalLog")
    CommonLog toEntity(CommonLogDto dto);
}