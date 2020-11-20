package com.gt.logbook.web.resource;

import static com.gt.logbook.web.resource.Paths.AUTHENTICATION_API_PATH;
import static com.gt.logbook.web.resource.Paths.BASE_API_PATH;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gt.logbook.web.dto.AuthenticationDto;
import com.gt.logbook.web.endpoint.AuthenticationEndpoint;

@RestController
@RequestMapping(path = BASE_API_PATH + AUTHENTICATION_API_PATH)
public class AuthenticationResource {

    private final AuthenticationEndpoint authenticationEndpoint;

    public AuthenticationResource(AuthenticationEndpoint authenticationEndpoint) {
        this.authenticationEndpoint = authenticationEndpoint;
    }

    @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void login(@Validated @RequestBody AuthenticationDto dto, HttpServletRequest request) {
        authenticationEndpoint.login(dto, request);
    }

    @PostMapping(path = "/logout")
    public void logout(HttpServletRequest request) {
        authenticationEndpoint.logout(request);
    }
}
