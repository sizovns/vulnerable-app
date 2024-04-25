package com.naham.bff.repository;

import com.naham.bff.model.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Long> {
    List<Order> findAllByUserId(long userId);
}
