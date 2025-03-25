package com.houserental.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.houserental.model.Tenant;

public interface TenantRepository extends MongoRepository<Tenant, String> {}