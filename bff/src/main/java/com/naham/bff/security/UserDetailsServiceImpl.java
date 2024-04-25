package com.naham.bff.security;

import com.naham.bff.model.dto.response.UserSystemInfoResponse;
import com.naham.bff.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userService.getUserInfoByUsername(username).map(this::create)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with login : " + username));
    }

    public UserDetails loadUserById(Long id) {
        return userService.getUserInfoById(id).map(this::create)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id : " + id));
    }

    private User create(UserSystemInfoResponse user) {
        return new Principal(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
    }
}
