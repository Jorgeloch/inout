package com.inout.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class Auth {
    UUID id;
    @Setter
    String email;
    @Setter
    String passwordHash;
}
