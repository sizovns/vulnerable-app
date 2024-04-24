package com.naham.api.service.impl;

import com.naham.api.exception.DaoException;
import com.naham.api.mapper.UserInfoMapper;
import com.naham.api.model.dto.request.CreateUserRequest;
import com.naham.api.model.dto.response.UserInfoResponse;
import com.naham.api.model.entity.UserInfo;
import com.naham.api.repository.RoleRepository;
import com.naham.api.repository.UserInfoRepository;
import com.naham.api.security.UserRole;
import com.naham.api.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;

@Slf4j
@Service
@Transactional
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository repository;
    private final RoleRepository roleRepository;
    private final UserInfoMapper mapper;

    public UserInfoServiceImpl(UserInfoRepository repository, RoleRepository roleRepository, UserInfoMapper mapper) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public UserInfoResponse getUserInfoById(Long userId) {
        UserInfo userInfo = repository.getReferenceById(userId);
        return mapper.mapUserInfoResponse(userInfo);
    }

    @Override
    public UserInfoResponse createUser(CreateUserRequest request) {
        try {
            UserInfo userInfo = mapper.mapUserInfo(request);
            if (request.isAdmin()) {
                userInfo.setRoles(Set.of(roleRepository.getReferenceById((long) UserRole.ROLE_ADMIN.getId())));
                log.info("Create user with role ROLE_ADMIN {}", userInfo);
            } else {
                userInfo.setRoles(Set.of(roleRepository.getReferenceById((long) UserRole.ROLE_USER.getId())));
                log.info("Create user with role ROLE_USER {}", userInfo);
            }
            return mapper.mapUserInfoResponse(repository.save(userInfo));
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Collection<UserInfoResponse> getUsers() {
        return repository.findAll()
                .stream()
                .map(mapper::mapUserInfoResponse)
                .toList();
    }
}
