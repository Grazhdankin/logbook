package com.gt.logbook.web.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonDeserialize(builder = PassageLogDto.PassageLogDtoBuilder.class)
public class PassageLogDto {

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

    @NotNull(message = "passage: must not be null")
    Float passage;

    @NotNull(message = "officerOfTheWatchId: must not be null")
    Long officerOfTheWatchId;

    @NotNull(message = "seamenOfTheWatchId: must not be null")
    Long seamenOfTheWatchId;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class PassageLogDtoBuilder {
    }
}