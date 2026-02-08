package com.inout.presentation.rest.controller;

import com.inout.application.dto.auth.requests.LogInRequestDTO;
import com.inout.application.dto.auth.requests.SignInRequestDTO;
import com.inout.application.dto.auth.responses.LogInResponseDTO;
import com.inout.application.usecase.auth.LogIn;
import com.inout.application.usecase.auth.SignIn;
import com.inout.domain.model.entities.Auth;
import com.inout.infrastructure.token.JWTTokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("v1/auth")
public class AuthController {
    private final SignIn signIn;
    private final LogIn logIn;
    private final JWTTokenService jwtTokenService;

    public AuthController(
            SignIn signIn,
            LogIn logIn,
            JWTTokenService jwtTokenService
    ) {
        this.logIn = logIn;
        this.signIn = signIn;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInRequestDTO dto) {
        Auth authUser = this.signIn.execute(dto);
        URI uri = UriComponentsBuilder
                .fromPath("/v1/auth/{id}")
                .buildAndExpand(authUser.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/login")
    public ResponseEntity<LogInResponseDTO> login(@RequestBody @Valid LogInRequestDTO dto) {
        Auth authUser = this.logIn.execute(dto);
        String token = this.jwtTokenService.generateToken(authUser);
        return ResponseEntity.ok(new LogInResponseDTO(token, "Bearer"));
    }
}
