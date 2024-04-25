package com.naham.bff.service;

import com.naham.bff.model.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(long productId);
}
