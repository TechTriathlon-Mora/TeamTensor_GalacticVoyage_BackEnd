package com.teamTensors.planetExploreservice.repository;

import com.teamTensors.planetExploreservice.model.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlanetRepository extends MongoRepository<Planet, String> {
}
