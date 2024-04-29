package com.naham.bff.controller;

import com.naham.bff.model.dto.request.AuthRequest;
import com.naham.bff.model.dto.response.AuthResponse;
import com.naham.bff.security.JwtTokenProvider;
import com.naham.bff.security.Principal;
import com.naham.bff.service.BucketService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping(path = "/auth", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final BucketService bucketService;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider tokenProvider, BucketService bucketService) {
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.bucketService = bucketService;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getUsername(),
                        authRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthResponse authResponse = new AuthResponse(tokenProvider.generateToken(authentication));
        bucketService.createBucketForUser(((Principal) authentication.getPrincipal()).getId());
        return ok(authResponse);
    }
}
