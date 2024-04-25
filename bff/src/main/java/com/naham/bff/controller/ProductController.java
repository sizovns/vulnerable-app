package com.naham.bff.controller;

import com.naham.bff.model.dto.response.ProductResponse;
import com.naham.bff.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = "/products", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable long productId) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductById(productId));
    }
}
