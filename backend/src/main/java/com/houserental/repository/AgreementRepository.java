package com.houserental.repository;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.houserental.model.RentalAgreement;

public interface AgreementRepository extends MongoRepository<RentalAgreement, String> {}