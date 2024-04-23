package com.naham.api.service.impl;

import com.naham.api.mapper.UserInfoMapper;
import com.naham.api.model.dto.request.CreateUserRequest;
import com.naham.api.model.dto.response.UserInfoResponse;
import com.naham.api.model.entity.UserInfo;
import com.naham.api.repository.UserInfoRepository;
import com.naham.api.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository repository;
    private final UserInfoMapper mapper;

    public UserInfoServiceImpl(UserInfoRepository repository, UserInfoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserInfoResponse getUserInfoById(Long userId) {
        UserInfo userInfo = repository.getReferenceById(userId);
        return mapper.mapUserInfoResponse(userInfo);
    }

    @Override
    public UserInfoResponse createUser(CreateUserRequest request) {
        UserInfo userInfo = mapper.mapUserInfo(request);
        return mapper.mapUserInfoResponse(repository.save(userInfo));
    }
}
