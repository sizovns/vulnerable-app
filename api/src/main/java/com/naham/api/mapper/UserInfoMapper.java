package com.naham.api.mapper;

import com.naham.api.model.dto.request.CreateUserRequest;
import com.naham.api.model.dto.response.UserInfoResponse;
import com.naham.api.model.dto.response.UserSystemInfoResponse;
import com.naham.api.model.entity.UserInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {
    UserInfoResponse mapUserInfoResponse(UserInfo source);

    UserInfo mapUserInfo(CreateUserRequest source);

    UserSystemInfoResponse mapUserSystemInfoResponse(UserInfo source);
}
