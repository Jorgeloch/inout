package com.inout.application.usecase.user;

import com.inout.domain.exception.user.UserNotFoundException;
import com.inout.domain.model.entities.User;
import com.inout.domain.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class GetUserUseCase {
    private final UserRepository userRepository;

    public GetUserUseCase(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public User execute(UUID userId) {
        return this.userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("user not found"));
    }
}
