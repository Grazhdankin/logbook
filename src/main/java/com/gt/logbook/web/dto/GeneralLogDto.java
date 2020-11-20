package com.gt.logbook.web.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = GeneralLogDto.GeneralLogDtoBuilder.class)
public class GeneralLogDto {

    @Null(groups = Group.Create.class, message = "id: must not present")
    @NotNull(groups = Group.Update.class, message = "id: must not be null")
    Long id;

    @Null(groups = Group.Create.class, message = "version: must not present")
    @NotNull(groups = Group.Update.class, message = "version: must not be null")
    Short version;

    @Null(message = "updatedAt: must not present")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime updatedAt;

    @NotNull(message = "date: must not be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate date;

    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime lightsOnTime;

    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime lightsOffTime;

    Float dieselFuelConsumption;

    Float dieselFuelRetention;

    Float heavyFuelConsumption;

    Float heavyFuelRetention;

    Float freshWaterConsumption;

    Float freshWaterRetention;

    @Size(max = 128, message = "name: size must be lower or equal to 128 characters")
    String note;

    @Null(message = "weatherLogs: must not present")
    Set<WeatherLogDto> weatherLogs;

    @Null(message = "passageLogs: must not present")
    Set<PassageLogDto> passageLogs;

    @Null(message = "tanksLogs: must not present")
    Set<TanksLogDto> tanksLogs;

    @Null(message = "commonLogs: must not present")
    Set<CommonLogDto> commonLogs;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class GeneralLogDtoBuilder {
    }
}