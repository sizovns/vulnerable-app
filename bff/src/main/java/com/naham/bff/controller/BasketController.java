package com.naham.bff.controller;

import com.naham.bff.model.dto.request.BasketRequest;
import com.naham.bff.model.dto.response.BasketResponse;
import com.naham.bff.security.Principal;
import com.naham.bff.service.BasketService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = "/baskets", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class BasketController {


    private final BasketService basketService;

    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @PostMapping
    public ResponseEntity<BasketResponse> addProductToBasket(@Valid @RequestBody BasketRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = ((Principal) authentication.getPrincipal()).getId();

        return ResponseEntity.status(HttpStatus.OK).body(basketService.addProductToBasket(request, userId));
    }

    @GetMapping
    public ResponseEntity<BasketResponse> getBasket() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = ((Principal) authentication.getPrincipal()).getId();

        return ResponseEntity.status(HttpStatus.OK).body(basketService.getBasketByUser(userId));
    }

    @DeleteMapping
    public ResponseEntity<BasketResponse> clearBasket() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = ((Principal) authentication.getPrincipal()).getId();

        return ResponseEntity.status(HttpStatus.OK).body(basketService.clearBasketByUser(userId));
    }
}
