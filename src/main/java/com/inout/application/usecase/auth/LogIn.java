package com.inout.application.usecase.auth;

import com.inout.application.dto.auth.requests.LogInRequestDTO;
import com.inout.domain.exception.auth.InvalidEmailException;
import com.inout.domain.exception.auth.InvalidPasswordException;
import com.inout.domain.model.entities.Auth;
import com.inout.domain.repository.auth.AuthRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LogIn {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public LogIn(
        AuthRepository authRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public Auth execute(
            LogInRequestDTO dto
    ) {
        Auth auth = authRepository
                .findByEmail(dto.email())
                .orElseThrow(() -> new InvalidEmailException(
                        "invalid email: no user with this email was found"
                ));

        if (!this.passwordEncoder.matches(dto.rawPassword(), auth.getPasswordHash())) {
            throw new InvalidPasswordException("Invalid password");
        };

        return auth;
    }
}
