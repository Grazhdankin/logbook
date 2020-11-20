package com.gt.logbook.web.endpoint;

import javax.servlet.http.HttpServletRequest;

import com.gt.logbook.web.dto.AuthenticationDto;

public interface AuthenticationEndpoint {

    void login(AuthenticationDto dto, HttpServletRequest request);

    void logout(HttpServletRequest request);
}
