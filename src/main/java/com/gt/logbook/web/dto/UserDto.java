package com.gt.logbook.web.dto;

import javax.validation.constraints.Email;
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
@JsonDeserialize(builder = UserDto.UserDtoBuilder.class)
public class UserDto {

    @Null(groups = Group.Create.class, message = "id: must not present")
    @NotNull(groups = Group.Update.class, message = "id: must not be null")
    Long id;

    @Null(groups = Group.Create.class, message = "version: must not present")
    @NotNull(groups = Group.Update.class, message = "version: must not be null")
    Short version;

    @NotBlank(message = "email: must not be blank")
    @Size(max = 255, message = "email: size must be lower or equal to 255 characters")
    @Email
    String email;

    String firstName;

    String lastName;

    String password;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class UserDtoBuilder {
    }
}