package com.gt.logbook.web.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

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

    @NotNull(message = "date: must not be null")
    LocalDate date;

    LocalTime lightsOnTime;

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
    List<WeatherLogDto> weatherLogs;

    @Null(message = "passageLogs: must not present")
    List<PassageLogDto> passageLogs;

    @Null(message = "tanksLogs: must not present")
    List<TanksLogDto> tanksLogs;

    @Null(message = "commonLogs: must not present")
    List<CommonLogDto> commonLogs;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class GeneralLogDtoBuilder {
    }
}