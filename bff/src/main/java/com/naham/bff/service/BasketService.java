package com.naham.bff.service;

import com.naham.bff.model.dto.request.BasketRequest;
import com.naham.bff.model.dto.response.BasketResponse;
import com.naham.bff.model.entity.Basket;

public interface BasketService {
    BasketResponse addProductToBasket(BasketRequest request, long userId);

    BasketResponse getBasketByUser(long userId);

    BasketResponse clearBasketByUser(long userId);

    Basket createBasketForUser(long userId);
}
