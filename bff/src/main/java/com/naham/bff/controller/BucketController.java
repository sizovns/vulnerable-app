package com.naham.bff.controller;

import com.naham.bff.model.dto.request.BucketRequest;
import com.naham.bff.model.dto.response.BucketResponse;
import com.naham.bff.security.Principal;
import com.naham.bff.service.BucketService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = "/buckets", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class BucketController {


    private final BucketService bucketService;

    public BucketController(BucketService bucketService) {
        this.bucketService = bucketService;
    }

    @PostMapping
    public ResponseEntity<BucketResponse> addProductToBucket(@Valid @RequestBody BucketRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = ((Principal) authentication.getPrincipal()).getId();

        return ResponseEntity.status(HttpStatus.OK).body(bucketService.addProductToBucket(request, userId));
    }

    @GetMapping
    public ResponseEntity<BucketResponse> getBucket() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = ((Principal) authentication.getPrincipal()).getId();

        return ResponseEntity.status(HttpStatus.OK).body(bucketService.getBucketByUser(userId));
    }

    @DeleteMapping
    public ResponseEntity<BucketResponse> clearBucket() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        long userId = ((Principal) authentication.getPrincipal()).getId();

        return ResponseEntity.status(HttpStatus.OK).body(bucketService.clearBucketByUser(userId));
    }
}
