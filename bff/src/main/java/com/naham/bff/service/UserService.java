package com.naham.bff.service;

import com.naham.bff.model.dto.response.UserSystemInfoResponse;

import java.util.Optional;

public interface UserService {
    Optional<UserSystemInfoResponse> getUserInfoById(long userId);

    Optional<UserSystemInfoResponse> getUserInfoByUsername(String username);
}
