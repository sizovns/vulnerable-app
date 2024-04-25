package com.naham.bff.service;

import com.naham.bff.model.dto.request.CreateOrderRequest;
import com.naham.bff.model.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    List<OrderResponse> getOrdersByUserId(long userId);

    OrderResponse getOrderById(Long orderId);

    OrderResponse createOrder(CreateOrderRequest request, long userId);
}
