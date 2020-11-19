package com.gt.logbook.web.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import com.gt.logbook.domain.entity.Tank;
import com.gt.logbook.web.dto.TankDto;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TankDtoMapper {

    TankDto toDto(Tank entity);

    List<TankDto> toDto(List<Tank> entityList);

    @Mapping(ignore = true, target = "updatedAt")
    Tank toEntity(TankDto dto);
}