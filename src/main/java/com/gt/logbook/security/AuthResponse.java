package com.gt.logbook.security;

import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class AuthResponse {

    String token;
}
