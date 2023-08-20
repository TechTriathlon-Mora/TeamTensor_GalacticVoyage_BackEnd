package com.teamTensors.planetExploreservice.service;

import com.teamTensors.planetExploreservice.model.Destination;
import com.teamTensors.planetExploreservice.model.Planet;
import com.teamTensors.planetExploreservice.model.PlanetList;
import com.teamTensors.planetExploreservice.repository.PlanetListRepository;
import com.teamTensors.planetExploreservice.repository.PlanetRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
@AllArgsConstructor
public class PlanetListService {

    private PlanetListRepository planetListRepository;
    private PlanetRepository planetRepository;


    public String createPlanetList(Planet planet){

        String planeId = planet.getId();
        String planetName = planet.getName();

        if(planeId == null || planetName == null){
            return "No values";
        }
        planetListRepository.save(new PlanetList(planeId,planetName));
        return "create planetList";
    }

    public ResponseEntity<List<PlanetList>> getFullPlanetList(){

        try {
            List<PlanetList> planetList = planetListRepository.findAll();

            if (planetList.isEmpty()) {
                return new ResponseEntity<>(Collections.emptyList(), HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(planetList, HttpStatus.OK);
        } catch (Exception e) {

            log.info("An error occurred while fetching planet list- {}", e.getMessage());
            return new ResponseEntity<>(Collections.emptyList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<PlanetList>> findByPartialName(String name){
        List<PlanetList> planets = planetListRepository.findPlanetsByPlanetNameContainingIgnoreCase(name);
        return ResponseEntity.ok(planets);
    }

}
