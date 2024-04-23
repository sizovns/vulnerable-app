package com.naham.api.service;

import com.naham.api.model.dto.request.CreateUserRequest;
import com.naham.api.model.dto.response.UserInfoResponse;

public interface UserInfoService {
    UserInfoResponse getUserInfoById(Long userId);

    UserInfoResponse createUser(CreateUserRequest request);
}
