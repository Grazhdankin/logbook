package com.gt.logbook.security;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
@JsonDeserialize(builder = AuthRequest.AuthRequestBuilder.class)
public class AuthRequest {

    @NotBlank(message = "username: must not be blank")
    String username;

    @NotBlank(message = "password: must not be blank")
    String password;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class AuthRequestBuilder {
    }
}
