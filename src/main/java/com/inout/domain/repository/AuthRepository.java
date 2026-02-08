package com.inout.domain.repository;

import com.inout.domain.model.entities.Auth;

import java.util.Optional;

public interface AuthRepository {
    void create(Auth auth);
    Optional<Auth> findByEmail(String email);
}
