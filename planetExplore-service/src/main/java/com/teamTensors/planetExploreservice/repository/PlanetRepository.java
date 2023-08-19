package com.teamTensors.planetExploreservice.repository;

import com.teamTensors.planetExploreservice.model.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {
}
