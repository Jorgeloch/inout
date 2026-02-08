package com.inout.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Auth {
    UUID id;
    String email;
    String passwordHash;

    public Auth(
            String email,
            String passwordHash
    ) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.passwordHash = passwordHash;
    }
}
