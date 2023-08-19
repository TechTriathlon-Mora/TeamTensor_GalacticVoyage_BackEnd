package com.teamTensors.planetExploreservice.service;

import com.teamTensors.planetExploreservice.dto.PlanetRequest;
import com.teamTensors.planetExploreservice.dto.PlanetResponse;
import com.teamTensors.planetExploreservice.model.Planet;
import com.teamTensors.planetExploreservice.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
//@RequiredArgsConstructor
@Slf4j
public class PlanetService {

    @Autowired
    private PlanetRepository planetRepository;

    public void createPlanet(PlanetRequest planetRequest) {
        Planet planet = Planet.builder()
                .name(planetRequest.getName())
                .description(planetRequest.getDescription())
                .distanceFromEarth(planetRequest.getDistanceFromEarth())
                .imageUrls(planetRequest.getImageUrls())
                .build();

        planetRepository.save(planet);
        log.info("Planet {} is saved", planet.getId());
    }

    public List<PlanetResponse> getAllPlanets() {
        System.out.println("planet is asked");
        List<Planet> planets = planetRepository.findAll();

        //return planets.stream().map(this::mapToPlanetResponse).toList();
        return planets.stream().map(this::mapToPlanetResponse).toList();
    }

    private PlanetResponse mapToPlanetResponse(Planet planet) {
        List<String> links = new ArrayList<>(planet.getImageUrls());
        log.info("Planet {} is returned", planet.getId());
        return PlanetResponse.builder()
                .id(planet.getId())
                .name(planet.getName())
                .description(planet.getDescription())
                .distanceFromEarth(planet.getDistanceFromEarth())
                .imageUrls(links)
                .build();
    }

    public List<Planet> getPlanets(){
        return planetRepository.findAll();
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
