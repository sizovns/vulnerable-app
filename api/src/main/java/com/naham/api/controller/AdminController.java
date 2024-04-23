package com.naham.api.controller;

import com.naham.api.model.dto.request.CreateUserRequest;
import com.naham.api.model.dto.response.UserInfoResponse;
import com.naham.api.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserInfoService userService;

    public AdminController(UserInfoService userService) {
        this.userService = userService;
    }


    @PostMapping(path = "/users", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserInfoResponse> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
    }
}
