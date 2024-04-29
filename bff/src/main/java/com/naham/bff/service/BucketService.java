package com.naham.bff.service;

import com.naham.bff.model.dto.request.BucketRequest;
import com.naham.bff.model.dto.response.BucketResponse;
import com.naham.bff.model.entity.Bucket;

public interface BucketService {
    BucketResponse addProductToBucket(BucketRequest request, long userId);

    BucketResponse getBucketByUser(long userId);

    BucketResponse clearBucketByUser(long userId);

    Bucket createBucketForUser(long userId);
}
