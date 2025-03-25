package com.houserental.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.houserental.model.House;

public interface HouseRepository extends MongoRepository<House, String> {}