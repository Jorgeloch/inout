package com.inout.domain.repository.user;


import com.inout.domain.model.entities.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    void create(User user);
    Optional<User> findById(UUID userId);
}
