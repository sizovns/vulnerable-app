package com.naham.api.controller;

import com.naham.api.model.dto.request.CreateCardRequest;
import com.naham.api.model.dto.request.UpdateCardRequest;
import com.naham.api.model.dto.response.CardResponse;
import com.naham.api.security.Principal;
import com.naham.api.service.PaymentCardService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping("/cards")
public class CardController {

    private final PaymentCardService service;

    public CardController(PaymentCardService service) {
        this.service = service;
    }

    @GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CardResponse>> getCards() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((Principal) authentication.getPrincipal()).getId();

        return ResponseEntity.status(HttpStatus.OK).body(service.getCardsByUser(userId));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CardResponse>> createCard(@Valid @RequestBody CreateCardRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((Principal) authentication.getPrincipal()).getId();

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createCardForUser(request, userId));
    }


    @PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CardResponse> updateCard(@Valid @RequestBody UpdateCardRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateCard(request));
    }

    @DeleteMapping(path = "/{cardId}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<CardResponse>> deleteCard(@PathVariable long cardId) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteCardById(cardId));
    }
}
