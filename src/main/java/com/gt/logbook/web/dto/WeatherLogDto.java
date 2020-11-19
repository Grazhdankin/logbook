package com.gt.logbook.web.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = WeatherLogDto.WeatherLogDtoBuilder.class)
public class WeatherLogDto {

    @Null(groups = Group.Create.class, message = "id: must not present")
    @NotNull(groups = Group.Update.class, message = "id: must not be null")
    Long id;

    @Null(groups = Group.Create.class, message = "version: must not present")
    @NotNull(groups = Group.Update.class, message = "version: must not be null")
    Short version;

    @Null(message = "updatedAt: must not present")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime updatedAt;

    @NotNull(message = "generalLogId: must not be null")
    Long generalLogId;

    @NotNull(message = "time: must not be null")
    @JsonFormat(pattern = "HH:mm:ss")
    LocalTime time;

    @Size(max = 16, message = "windDirectionAndVelocity: size must be lower or equal to 16 characters")
    String windDirectionAndVelocity;

    @Size(max = 16, message = "sea: size must be lower or equal to 16 characters")
    String sea;

    @Size(max = 16, message = "weatherAndVisibility: size must be lower or equal to 16 characters")
    String weatherAndVisibility;

    Short airPressure;

    Float airTemperature;

    Float waterTemperature;

    Short cargoHolds;

    Short bildgeWaterLevel;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class WeatherLogDtoBuilder {
    }
}