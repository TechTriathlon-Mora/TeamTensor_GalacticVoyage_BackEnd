package com.teamTensors.planetExploreservice.controller;

import com.teamTensors.planetExploreservice.dto.PlanetRequest;
import com.teamTensors.planetExploreservice.dto.PlanetResponse;
import com.teamTensors.planetExploreservice.model.Planet;
import com.teamTensors.planetExploreservice.repository.PlanetRepository;
import com.teamTensors.planetExploreservice.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/planet")
//@RequiredArgsConstructor
public class PlanetController {

    @Autowired
    private PlanetService planetService;

    @Autowired
    private PlanetRepository planetRepository;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPlanet(@RequestBody PlanetRequest planetRequest) {
        planetService.createPlanet(planetRequest);
        return "Created";
    }

//    @GetMapping
//    public List<Planet> getAllPlanets() {
//        List<Planet> planets = new ArrayList<Planet>();
//        planetRepository.findAll().forEach(planets::add);
//        return planets;
//    }

    @GetMapping
    public ResponseEntity<List<Planet>> getAllPlanets() {
        List<Planet> planets = planetService.getPlanets();
        if (planets.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(planets);
    }




    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAll() {
        return planetService.deleteAllPlanets();
    }
}
