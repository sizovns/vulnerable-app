package com.naham.api.security;

public enum UserRole {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER"),
    ROLE_SYSTEM("ROLE_SYSTEM");

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
