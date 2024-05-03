package com.naham.bff.service.impl;

import com.naham.bff.mapper.OrderMapper;
import com.naham.bff.model.dto.request.CreateOrderRequest;
import com.naham.bff.model.dto.response.CardResponse;
import com.naham.bff.model.dto.response.OrderResponse;
import com.naham.bff.model.entity.Basket;
import com.naham.bff.model.entity.Order;
import com.naham.bff.repository.BasketRepository;
import com.naham.bff.repository.OrderRepository;
import com.naham.bff.service.CardService;
import com.naham.bff.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BasketRepository basketRepository;
    private final CardService cardService;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(OrderRepository orderRepository, BasketRepository basketRepository,
                            CardService cardService, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.basketRepository = basketRepository;
        this.cardService = cardService;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<OrderResponse> getOrdersByUserId(long userId) {
        List<Order> orders = orderRepository.findAllByUserId(userId);
        if (orders == null) {
            orders = Collections.emptyList();
        }
        return orders.stream().map(orderMapper::mapOrderResponse).toList();
    }

    @Override
    public OrderResponse getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::mapOrderResponse)
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public OrderResponse createOrder(CreateOrderRequest request, long userId) {
        Basket basket = basketRepository.findByUserId(userId);
        if (basket == null || !basket.getId().equals(request.getBasketId())) {
            throw new IllegalArgumentException("No Basket with id " + request.getBasketId() + " for userID " +
                    userId);
        }
        List<CardResponse> cards = cardService.getCardsByUserId(userId).getCards();
        if (cards == null || cards.isEmpty() || cards.stream().noneMatch(card -> card.getId() == request.getCardId())) {
            throw new IllegalArgumentException("No card with id " + request.getCardId() + "for userID " +
                    userId);
        }

        Order order = new Order();
        order.setProducts(basket.getProducts());
        order.setUserId(userId);
        order.setPaymentCardId(request.getCardId());
        return orderMapper.mapOrderResponse(orderRepository.save(order));
    }
}
