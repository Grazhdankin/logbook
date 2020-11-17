package com.gt.logbook.web.dto;

import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = CommonLogDto.CommonLogDtoBuilder.class)
public class CommonLogDto {

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

    String location;

    @NotBlank(message = "log: must not be blank")
    @Size(max = 255, message = "log: size must be lower or equal to 255 characters")
    String log;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class CommonLogDtoBuilder {
    }
}