package com.naham.api.security;

import com.naham.api.model.entity.Role;
import com.naham.api.model.entity.UserInfo;
import com.naham.api.repository.RoleRepository;
import com.naham.api.repository.UserInfoRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserInfoRepository usersRepository;

    public UserDetailsServiceImpl(UserInfoRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usersRepository.findByUsername(username).map(this::create)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with login : " + username));
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        return usersRepository.findById(id).map(this::create)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with id : " + id)
                );
    }

    private User create(UserInfo user) {
        return new Principal(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName().getName())).collect(Collectors.toList()));
    }
}
