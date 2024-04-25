package com.naham.bff.repository;

import com.naham.bff.model.entity.Bucket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BucketRepository extends MongoRepository<Bucket, Long> {
    Bucket findByUserId(Long userId);
}
