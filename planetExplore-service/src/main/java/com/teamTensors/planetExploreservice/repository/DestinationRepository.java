package com.teamTensors.planetExploreservice.repository;

import com.teamTensors.planetExploreservice.model.Destination;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface DestinationRepository extends MongoRepository<Destination,String> {
    List<Destination> findByPlanetNameContaining(String planeName);
    List<Destination> findByPublished(boolean published);
}
