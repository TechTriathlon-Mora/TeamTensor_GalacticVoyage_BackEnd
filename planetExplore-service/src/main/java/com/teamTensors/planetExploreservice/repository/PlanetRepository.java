package com.teamTensors.planetExploreservice.repository;

import com.teamTensors.planetExploreservice.model.Planet;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {

    @Query("{}")
    List<Planet> getAllPlanets();

}
