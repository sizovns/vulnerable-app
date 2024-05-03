package com.naham.api.service;

import com.naham.api.model.dto.request.CreateUserRequest;
import com.naham.api.model.dto.response.UserInfoResponse;
import com.naham.api.model.dto.response.UserSystemInfoResponse;

import java.util.Collection;

public interface UserInfoService {
    UserInfoResponse getUserInfoById(Long userId);

    UserInfoResponse createUser(CreateUserRequest request);

    Collection<UserInfoResponse> getUsers();

    UserSystemInfoResponse getUserSystemInfoById(long userId);

    UserSystemInfoResponse getUserSystemInfoByUsername(String username);

    UserInfoResponse disableUser(long userId);
}
