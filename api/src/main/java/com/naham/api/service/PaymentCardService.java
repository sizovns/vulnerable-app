package com.naham.api.service;

import com.naham.api.model.dto.request.CreateCardRequest;
import com.naham.api.model.dto.request.UpdateCardRequest;
import com.naham.api.model.dto.response.CardResponse;

import java.util.Collection;

public interface PaymentCardService {
    Collection<CardResponse> getCardsByUser(Long userId);

    Collection<CardResponse> createCardForUser(CreateCardRequest request, Long userId);

    CardResponse updateCard(UpdateCardRequest request);

    Collection<CardResponse> deleteCardById(long cardId, Long userId);

    CardResponse getCardById(Long cardId);
}
