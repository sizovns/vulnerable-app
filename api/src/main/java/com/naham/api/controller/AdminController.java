package com.naham.api.controller;

import com.naham.api.model.dto.request.CreateUserRequest;
import com.naham.api.model.dto.response.UserInfoResponse;
import com.naham.api.service.UserInfoService;
import jakarta.persistence.PersistenceException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = "/admin", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class AdminController {

    private final UserInfoService userService;

    public AdminController(UserInfoService userService) {
        this.userService = userService;
    }


    @PostMapping(path = "/users")
    public ResponseEntity<UserInfoResponse> createUser(@RequestBody CreateUserRequest request) {
        log.info("createUser request {}", request);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(request));
        } catch (PersistenceException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        }
    }

    @GetMapping(path = "/users")
    public ResponseEntity<Collection<UserInfoResponse>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @DeleteMapping(path = "/users/{userId}")
    public ResponseEntity<UserInfoResponse> disableUser(@PathVariable long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.disableUser(userId));
    }
}
