package com.naham.bff.service;

import com.naham.bff.model.dto.response.CardListResponse;

public interface CardService {
    CardListResponse getCardsByUserId(long userId);

    String getCardById(String cardId);
}
