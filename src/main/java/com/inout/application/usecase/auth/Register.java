package com.inout.application.usecase.auth;

import com.inout.application.dto.auth.requests.SignInRequestDTO;
import com.inout.domain.exception.auth.EmailAlreadyExistsException;
import com.inout.domain.model.entities.Auth;
import com.inout.domain.model.entities.User;
import com.inout.domain.repository.auth.AuthRepository;
import com.inout.domain.repository.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class Register {
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    public Register(
            UserRepository userRepository,
            AuthRepository authRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
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

        UUID userId = UUID.randomUUID();
        String passwordHash = this.passwordEncoder.encode(dto.rawPassword());

        Auth auth = new Auth(
                userId,
                dto.email(),
                passwordHash
        );
        this.authRepository.create(auth);

        User user = new User(
                userId,
                dto.name()
        );
        this.userRepository.create(user);

        return auth;
    }
}
