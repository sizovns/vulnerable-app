package com.naham.bff.repository;

import com.naham.bff.model.entity.Basket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BasketRepository extends MongoRepository<Basket, Long> {
    Basket findByUserId(Long userId);
}
