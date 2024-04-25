package com.naham.bff.controller;

import com.naham.bff.model.dto.request.CreateOrderRequest;
import com.naham.bff.model.dto.response.OrderResponse;
import com.naham.bff.security.Principal;
import com.naham.bff.service.OrderService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = "/orders", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> getOrders() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = ((Principal) authentication.getPrincipal()).getId();

        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrdersByUserId(userId));
    }


    @GetMapping("/{orderId}") // IDOR
    public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(orderId));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody CreateOrderRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = ((Principal) authentication.getPrincipal()).getId();

        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request, userId));
    }


}
