package com.naham.bff.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class Principal extends User {

    private final Long id;

    public Principal(Long id, String email, String password, boolean enabled, Collection<? extends GrantedAuthority> authorities) {
        super(email, password, enabled, true, true, true, authorities);
        this.id = id;
    }
}