package com.gt.logbook.web.dto;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = AuthenticationDto.AuthenticationDtoBuilder.class)
public class AuthenticationDto {

    @NotBlank(message = "userName: must not be blank")
    String userName;

    @NotBlank(message = "password: must not be blank")
//    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",
//            message = "password: must contains minimum eight characters, at least one letter and one number")
    String password;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class AuthenticationDtoBuilder {
    }
}
