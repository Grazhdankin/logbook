package com.gt.logbook.web.endpoint.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import com.gt.logbook.web.dto.AuthenticationDto;
import com.gt.logbook.web.endpoint.AuthenticationEndpoint;

@Service
public class DefaultAuthenticationEndpoint implements AuthenticationEndpoint {

    @Resource(name="authenticationProvider")
    private DaoAuthenticationProvider authenticationProvider;

    @Override
    public void login(AuthenticationDto dto, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(dto.getUserName(), dto.getPassword());
        Authentication authentication = authenticationProvider.authenticate(authenticationToken);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }

    @Override
    public void logout(HttpServletRequest request) {
        new SecurityContextLogoutHandler().logout(request, null, null);
    }
}
