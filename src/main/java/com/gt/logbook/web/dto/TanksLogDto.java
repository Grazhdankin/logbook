package com.gt.logbook.web.dto;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = TanksLogDto.TanksLogDtoBuilder.class)
public class TanksLogDto {

    @Null(groups = Group.Create.class, message = "id: must not present")
    @NotNull(groups = Group.Update.class, message = "id: must not be null")
    Long id;

    @Null(groups = Group.Create.class, message = "version: must not present")
    @NotNull(groups = Group.Update.class, message = "version: must not be null")
    Short version;

    @NotNull(message = "generalLogId: must not be null")
    Long generalLogId;

    @NotNull(message = "time: must not be null")
    LocalTime time;

    @NotNull(message = "tank: must not be null")
    TankDto tank;

    Float ballastWater;

    Float fuel;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class TanksLogDtoBuilder {
    }
}