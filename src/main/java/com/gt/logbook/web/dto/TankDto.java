package com.gt.logbook.web.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
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
@JsonDeserialize(builder = TankDto.TankDtoBuilder.class)
public class TankDto {

    @Null(groups = Group.Create.class, message = "id: must not present")
    @NotNull(groups = Group.Update.class, message = "id: must not be null")
    Long id;

    @Null(groups = Group.Create.class, message = "version: must not present")
    @NotNull(groups = Group.Update.class, message = "version: must not be null")
    Short version;

    @Null(message = "updatedAt: must not present")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime updatedAt;

    @NotBlank(message = "name: must not be blank")
    @Size(max = 32, message = "name: size must be lower or equal to 32 characters")
    String name;

    @Size(max = 128, message = "name: size must be lower or equal to 128 characters")
    String description;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class TankDtoBuilder {
    }
}