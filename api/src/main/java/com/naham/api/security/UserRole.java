package com.naham.api.security;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum UserRole {
    ROLE_ADMIN("ROLE_ADMIN", 1),
    ROLE_SYSTEM("ROLE_SYSTEM", 2),
    ROLE_USER("ROLE_USER", 3);

    private final String name;
    private final int id;

    UserRole(String name, int id) {
        this.name = name;
        this.id = id;
    }
}
