package com.naham.bff.service.impl;

import com.naham.bff.mapper.BasketMapper;
import com.naham.bff.model.dto.request.BasketRequest;
import com.naham.bff.model.dto.response.BasketResponse;
import com.naham.bff.model.entity.Basket;
import com.naham.bff.model.entity.Product;
import com.naham.bff.repository.BasketRepository;
import com.naham.bff.repository.ProductRepository;
import com.naham.bff.service.BasketService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final BasketMapper basketMapper;

    public BasketServiceImpl(ProductRepository productRepository, BasketRepository basketRepository, BasketMapper basketMapper) {
        this.productRepository = productRepository;
        this.basketRepository = basketRepository;
        this.basketMapper = basketMapper;
    }

    @Override
    public BasketResponse addProductToBasket(BasketRequest request, long userId) {
        Basket basket = basketRepository.findByUserId(userId);

        if (basket == null) {
            basket = createBasketForUser(userId);
        }
        Optional<Product> product = productRepository.findById(request.getProductId());
        if (product.isEmpty()) {
            return basketMapper.mapProductResponse(basket);
        }
        for (int i = 0; i < request.getCount(); i++) {
            basket.getProducts().add(product.get());
        }
        return basketMapper.mapProductResponse(basketRepository.save(basket));
    }

    @Override
    public BasketResponse getBasketByUser(long userId) {
        Basket basket = basketRepository.findByUserId(userId);
        if (basket == null) {
            basket = createBasketForUser(userId);
        }
        return basketMapper.mapProductResponse(basket);
    }

    @Override
    public BasketResponse clearBasketByUser(long userId) {
        Basket basket = basketRepository.findByUserId(userId);
        if (basket == null) {
            throw new IllegalArgumentException();
        }
        basket.setProducts(new ArrayList<>());
        return basketMapper.mapProductResponse(basketRepository.save(basket));
    }

    @Override
    public Basket createBasketForUser(long userId) {
        Basket userBasket = basketRepository.findByUserId(userId);
        if (userBasket != null) {
            return userBasket;
        }
        Basket basket = new Basket();
        basket.setUserId(userId);
        basket.setProducts(new ArrayList<>());
        return basketRepository.save(basket);
    }
}
