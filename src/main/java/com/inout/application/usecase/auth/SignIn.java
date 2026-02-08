package com.inout.application.usecase.auth;

import com.inout.application.dto.auth.requests.SignInRequestDTO;
import com.inout.domain.exception.auth.EmailAlreadyExistsException;
import com.inout.domain.model.entities.Auth;
import com.inout.domain.repository.AuthRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SignIn {
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public SignIn(
            AuthRepository authRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authRepository = authRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Auth execute(
            SignInRequestDTO dto
    ) {
        this.authRepository
                .findByEmail(dto.email())
                .ifPresent(auth -> {
                    throw new EmailAlreadyExistsException("email already in use");
                });

        String passwordHash = this.passwordEncoder.encode(dto.rawPassword());

        Auth auth = new Auth(
                dto.email(),
                passwordHash
        );

        this.authRepository.create(auth);

        return auth;
    }
}
