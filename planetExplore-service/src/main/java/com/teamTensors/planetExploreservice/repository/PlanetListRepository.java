package com.teamTensors.planetExploreservice.repository;

import com.teamTensors.planetExploreservice.model.PlanetList;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetListRepository extends MongoRepository<PlanetList,String> {

    @Query("{ 'planetName': { $regex: ?0, $options: 'i' } }")
    List<PlanetList> findPlanetsByPlanetNameContainingIgnoreCase(String partialName);
}
