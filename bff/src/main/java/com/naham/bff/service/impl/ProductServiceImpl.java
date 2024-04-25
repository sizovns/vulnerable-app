package com.naham.bff.service.impl;

import com.naham.bff.mapper.ProductMapper;
import com.naham.bff.model.dto.response.ProductResponse;
import com.naham.bff.repository.ProductRepository;
import com.naham.bff.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public ProductServiceImpl(ProductRepository repository, ProductMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::mapProductResponse)
                .toList();
    }

    @Override
    public ProductResponse getProductById(long productId) {
        return repository.findById(productId)
                .map(mapper::mapProductResponse)
                .orElse(null);
    }
}
