package com.naham.bff.service.impl;

import com.naham.bff.model.dto.response.CardListResponse;
import com.naham.bff.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.nio.file.Paths;
import java.util.Collection;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Service
public class CardServiceImpl implements CardService {

    @Value("${auth.uri}")
    private String uri;

    @Value("${security.jwt.token}")
    private String token;

    private final RestClient restClient = RestClient.create();


    @Override
    public CardListResponse getCardsByUserId(long userId) {
        CardListResponse response = new CardListResponse();
        response.setCards(
                restClient.get().uri(uri + "/system/cards/" + userId)
                        .header("Authorization", "Bearer " + token)
                        .header("Content-Type", APPLICATION_JSON_VALUE)
                        .retrieve()
                        .body(Collection.class)
        );
        return response;
    }

    @Override
    public String getCardById(String cardId) {
        String path = uri + Paths.get("/system/cards/", cardId).normalize();
        log.info("Send request to uri {}", path);
        return restClient.get().uri(path)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", APPLICATION_JSON_VALUE)
                .retrieve()
                .body(String.class);

    }
}
