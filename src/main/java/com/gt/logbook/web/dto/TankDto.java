package com.gt.logbook.web.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = TankDto.TankDtoBuilder.class)
public class TankDto {

    String name;
    String description;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class TankDtoBuilder {
    }
}
