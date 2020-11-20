package com.gt.logbook.web.resource;

import static com.gt.logbook.web.resource.Paths.AUTHENTICATION_API_PATH;
import static com.gt.logbook.web.resource.Paths.BASE_API_PATH;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.gt.logbook.security.AuthRequest;
import com.gt.logbook.security.AuthResponse;
import com.gt.logbook.security.JWTUtil;
import com.gt.logbook.security.PBKDF2Encoder;
import com.gt.logbook.web.endpoint.AuthenticationEndpoint;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = BASE_API_PATH + AUTHENTICATION_API_PATH)
public class AuthenticationResource {

    private final JWTUtil jwtUtil;

    private final PBKDF2Encoder passwordEncoder;

    private final AuthenticationEndpoint authenticationEndpoint;

    public AuthenticationResource(JWTUtil jwtUtil,
                                  PBKDF2Encoder passwordEncoder,
                                  AuthenticationEndpoint authenticationEndpoint) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.authenticationEndpoint = authenticationEndpoint;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest authRequest) {
        return authenticationEndpoint.findByUsername(authRequest.getUsername())
                .map((user) -> {
                    if (passwordEncoder.encode(authRequest.getPassword()).equals(user.getPassword())) {
                        return ResponseEntity.ok(AuthResponse.builder().token(jwtUtil.generateToken(user)).build());
                    } else {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                    }
                })
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}
