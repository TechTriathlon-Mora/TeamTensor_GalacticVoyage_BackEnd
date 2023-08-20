package com.teamTensors.planetExploreservice.service;

import com.teamTensors.planetExploreservice.dto.PlanetRequest;
import com.teamTensors.planetExploreservice.dto.PlanetResponse;
import com.teamTensors.planetExploreservice.model.Destination;
import com.teamTensors.planetExploreservice.model.Planet;
import com.teamTensors.planetExploreservice.repository.PlanetRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
@Slf4j
@AllArgsConstructor
public class PlanetService {

    private PlanetRepository planetRepository;
    private PlanetListService planetListService;

    public void createPlanet(PlanetRequest planetRequest) {
        Planet planet = Planet.builder()
                .name(planetRequest.getName())
                .description(planetRequest.getDescription())
                .distanceFromEarth(planetRequest.getDistanceFromEarth())
                .imageUrls(planetRequest.getImageUrls())
                .build();

        planetRepository.save(planet);
        log.info("Planet {} is saved", planet.getId());

        planetListService.createPlanetList(planet);
    }


    public ResponseEntity<List<Planet>> getAllPlanets() {

        try {
            List<Planet> planets = planetRepository.getAllPlanets();

            if(planets.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(planets, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    public ResponseEntity<Planet> getPlanetById(String id){
        Optional<Planet> data = planetRepository.findById(id);

        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    public ResponseEntity<HttpStatus> deleteAllPlanets(){
        try {
            planetRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
