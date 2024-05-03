package com.naham.bff.service.impl;

import com.naham.bff.model.dto.request.AuthRequest;
import com.naham.bff.model.dto.response.UserSystemInfoResponse;
import com.naham.bff.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
public class UserServiceImpl implements UserService {

    @Value("${auth.uri}")
    private String uri;

    @Value("${security.jwt.token}")
    private String token;

    private final RestClient restClient = RestClient.create();

    @Override
    public Optional<UserSystemInfoResponse> getUserInfoById(long userId) {
        return Optional.ofNullable(restClient.get().uri(uri + "/system/users/" + userId)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", APPLICATION_JSON_VALUE)
                .retrieve()
                .body(UserSystemInfoResponse.class));
    }

    @Override
    public Optional<UserSystemInfoResponse> getUserInfoByUsername(String username) {
        return Optional.ofNullable(restClient.get().uri(uri + "/system/users?username=" + username)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", APPLICATION_JSON_VALUE)
                .retrieve()
                .body(UserSystemInfoResponse.class));
    }

    @Override
    public Optional<UserSystemInfoResponse> createUser(AuthRequest request) {
        return Optional.ofNullable(restClient.post().uri(uri + "/system/users")
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", APPLICATION_JSON_VALUE)
                .body(request)
                .retrieve()
                .body(UserSystemInfoResponse.class));
    }
}
