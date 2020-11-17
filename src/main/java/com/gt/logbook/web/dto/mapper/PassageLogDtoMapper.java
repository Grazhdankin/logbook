package com.gt.logbook.web.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.gt.logbook.domain.entity.PassageLog;
import com.gt.logbook.web.dto.PassageLogDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {GeneralLogDtoMapper.class, UserDtoMapper.class})
public interface PassageLogDtoMapper {

    @Mapping(source="generalLog.id", target="generalLogId")
    @Mapping(source="officerOfTheWatch.id", target="officerOfTheWatchId")
    @Mapping(source="seamenOfTheWatch.id", target="seamenOfTheWatchId")
    PassageLogDto toDto(PassageLog entity);

    List<PassageLogDto> toDto(List<PassageLog> entityList);

    @Mapping(source = "generalLogId", target = "generalLog")
    @Mapping(source = "officerOfTheWatchId", target = "officerOfTheWatch")
    @Mapping(source = "seamenOfTheWatchId", target = "seamenOfTheWatch")
    PassageLog toEntity(PassageLogDto dto);
}
