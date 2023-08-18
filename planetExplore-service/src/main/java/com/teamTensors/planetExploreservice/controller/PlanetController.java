package com.teamTensors.planetExploreservice.controller;

import com.teamTensors.planetExploreservice.dto.PlanetRequest;
import com.teamTensors.planetExploreservice.dto.PlanetResponse;
import com.teamTensors.planetExploreservice.service.PlanetService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planet")
//@RequiredArgsConstructor
public class PlanetController {

    @Autowired
    private PlanetService planetService;

//    public PlanetController(PlanetService planetService) {
//        this.planetService = planetService;
//    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createPlanet(@RequestBody PlanetRequest planetRequest) {
        planetService.createPlanet(planetRequest);
        return "Created";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PlanetResponse> getAllProducts() {
        return planetService.getAllPlanets();
    }
}
