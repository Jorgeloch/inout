package com.inout.domain.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class User {
    UUID id;
    @Setter
    String name;
}
