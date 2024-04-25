package com.naham.api.controller;

import com.naham.api.model.dto.response.CardResponse;
import com.naham.api.model.dto.response.UserInfoResponse;
import com.naham.api.model.dto.response.UserSystemInfoResponse;
import com.naham.api.service.PaymentCardService;
import com.naham.api.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = "/system", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class SystemController {
    private final UserInfoService userService;
    private final PaymentCardService cardService;

    public SystemController(UserInfoService userService, PaymentCardService cardService) {
        this.userService = userService;
        this.cardService = cardService;
    }


    @GetMapping(path = "/users/{userId}")
    public ResponseEntity<UserSystemInfoResponse> getUserByUsername(@PathVariable long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserSystemInfoById(userId));
    }

    @GetMapping(path = "/users")
    public ResponseEntity<UserSystemInfoResponse> getUserByUsername(@RequestParam(name = "username") String username) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUserSystemInfoByUsername(username));
    }

    @GetMapping(path = "/all/users")
    public ResponseEntity<Collection<UserInfoResponse>> getUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getUsers());
    }

    @GetMapping(path = "/cards/{userId}")
    public ResponseEntity<Collection<CardResponse>> getCards(@PathVariable Long userId) {
        return ResponseEntity.status(HttpStatus.OK).body(cardService.getCardsByUser(userId));
    }
}
