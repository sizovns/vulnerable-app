package com.naham.bff.service.impl;

import com.naham.bff.mapper.BucketMapper;
import com.naham.bff.model.dto.request.BucketRequest;
import com.naham.bff.model.dto.response.BucketResponse;
import com.naham.bff.model.entity.Bucket;
import com.naham.bff.model.entity.Product;
import com.naham.bff.repository.BucketRepository;
import com.naham.bff.repository.ProductRepository;
import com.naham.bff.service.BucketService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class BucketServiceImpl implements BucketService {

    private final ProductRepository productRepository;
    private final BucketRepository bucketRepository;
    private final BucketMapper bucketMapper;

    public BucketServiceImpl(ProductRepository productRepository, BucketRepository bucketRepository, BucketMapper bucketMapper) {
        this.productRepository = productRepository;
        this.bucketRepository = bucketRepository;
        this.bucketMapper = bucketMapper;
    }

    @Override
    public BucketResponse addProductToBucket(BucketRequest request, long userId) {
        Bucket bucket = bucketRepository.findByUserId(userId);

        if (bucket == null) {
            bucket = createBucketForUser(userId);
        }
        Optional<Product> product = productRepository.findById(request.getProductId());
        if (product.isEmpty()) {
            return bucketMapper.mapProductResponse(bucket);
        }
        for (int i = 0; i < request.getCount(); i++) {
            bucket.getProducts().add(product.get());
        }
        return bucketMapper.mapProductResponse(bucketRepository.save(bucket));
    }

    @Override
    public BucketResponse getBucketByUser(long userId) {
        Bucket bucket = bucketRepository.findByUserId(userId);
        if (bucket == null) {
            bucket = createBucketForUser(userId);
        }
        return bucketMapper.mapProductResponse(bucket);
    }

    @Override
    public BucketResponse clearBucketByUser(long userId) {
        Bucket bucket = bucketRepository.findByUserId(userId);
        if (bucket == null) {
            throw new IllegalArgumentException();
        }
        bucket.setProducts(new ArrayList<>());
        return bucketMapper.mapProductResponse(bucketRepository.save(bucket));
    }

    @Override
    public Bucket createBucketForUser(long userId) {
        Bucket bucket = new Bucket();
        bucket.setUserId(userId);
        bucket.setProducts(new ArrayList<>());
        return bucketRepository.save(bucket);
    }
}
