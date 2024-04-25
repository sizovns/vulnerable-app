package com.naham.bff.controller;

import com.naham.bff.model.dto.response.CardResponse;
import com.naham.bff.security.Principal;
import com.naham.bff.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = "/cards", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/my")
    public ResponseEntity<List<CardResponse>> getMyCards() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = ((Principal) authentication.getPrincipal()).getId();

        List<CardResponse> cards = cardService.getCardsByUserId(userId).getCards();
        return ResponseEntity.status(HttpStatus.OK).body(cards);
    }

    // path traversal secondary context
    @GetMapping
    public ResponseEntity<String> getCardById(@RequestParam(name = "cardId") String cardId) {
        String card = cardService.getCardById(cardId);
        return ResponseEntity.status(HttpStatus.OK).body(card);
    }
}
