package com.teamTensors.planetExploreservice.repository;

import com.teamTensors.planetExploreservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
