package com.gt.logbook.web.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.gt.logbook.domain.entity.GeneralLog;
import com.gt.logbook.web.dto.GeneralLogDto;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {WeatherLogDtoMapper.class, PassageLogDtoMapper.class, TanksLogDtoMapper.class, CommonLogDtoMapper.class})
public interface GeneralLogDtoMapper {

    GeneralLogDto toDto(GeneralLog entity);

    List<GeneralLogDto> toDto(List<GeneralLog> entityList);

    @Mapping(ignore = true, target = "weatherLogs")
    @Mapping(ignore = true, target = "passageLogs")
    @Mapping(ignore = true, target = "tanksLogs")
    @Mapping(ignore = true, target = "commonLogs")
    GeneralLog toEntity(GeneralLogDto dto);

    default GeneralLog fromId(final Long id) {
        if (id == null) {
            return null;
        }

        final GeneralLog generalLog = new GeneralLog();
        generalLog.setId(id);

        return generalLog;
    }
}