package com.naham.api.service.impl;

import com.naham.api.exception.DaoException;
import com.naham.api.mapper.PaymentCardMapper;
import com.naham.api.model.dto.request.CreateCardRequest;
import com.naham.api.model.dto.request.UpdateCardRequest;
import com.naham.api.model.dto.response.CardResponse;
import com.naham.api.model.entity.PaymentCard;
import com.naham.api.model.entity.UserInfo;
import com.naham.api.repository.PaymentCardRepository;
import com.naham.api.repository.UserInfoRepository;
import com.naham.api.service.PaymentCardService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentCardServiceImpl implements PaymentCardService {

    private final PaymentCardRepository repository;
    private final UserInfoRepository userInfoRepository;
    private final PaymentCardMapper mapper;

    public PaymentCardServiceImpl(PaymentCardRepository repository, UserInfoRepository userInfoRepository, PaymentCardMapper mapper) {
        this.repository = repository;
        this.userInfoRepository = userInfoRepository;
        this.mapper = mapper;
    }

    @Override
    public Collection<CardResponse> getCardsByUser(Long userId) {
        Set<PaymentCard> cards = userInfoRepository.getReferenceById(userId).getCards();
        if (cards == null) {
            return Collections.emptyList();
        }
        return cards.stream().map(mapper::mapCardResponse).toList();
    }

    @Override
    public Collection<CardResponse> createCardForUser(CreateCardRequest request, Long userId) {
        try {
            UserInfo userInfo = userInfoRepository.getReferenceById(userId);
            Set<PaymentCard> cards = userInfo.getCards();
            if (cards == null) {
                cards = new HashSet<>();
                userInfo.setCards(cards);
            }
            PaymentCard paymentCard = mapper.mapPaymentCard(request);
            cards.add(paymentCard);
            return userInfoRepository.saveAndFlush(userInfo).getCards().stream().map(mapper::mapCardResponse).toList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public CardResponse updateCard(UpdateCardRequest request) {
        try {
            if (!repository.existsById(request.getId())) {
                return null;
            }
            PaymentCard paymentCard = repository.saveAndFlush(mapper.mapPaymentCard(request));
            return mapper.mapCardResponse(paymentCard);
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }

    @Override
    public Collection<CardResponse> deleteCardById(long cardId, Long userId) {
        try {
            List<PaymentCard> all = repository.findAll();
            boolean empty = all.stream().filter(card -> card.getId() == cardId).findFirst().isEmpty();
            if (!empty) {
                UserInfo userInfo = userInfoRepository.getReferenceById(userId);
                userInfo.setCards(userInfo.getCards().stream().filter(card -> card.getId() != cardId).collect(Collectors.toSet()));
                userInfoRepository.saveAndFlush(userInfo);
                repository.deleteById(cardId);
            }
            return all.stream().filter(card -> card.getId() != cardId).map(mapper::mapCardResponse).toList();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
