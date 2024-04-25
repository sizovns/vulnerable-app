package com.naham.bff.repository;

import com.naham.bff.model.entity.UserInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserInfoRepository extends MongoRepository<UserInfo, Long> {
}
