package com.teamTensors.planetExploreservice.service;

import com.teamTensors.planetExploreservice.dto.PlanetRequest;
import com.teamTensors.planetExploreservice.dto.PlanetResponse;
import com.teamTensors.planetExploreservice.model.Planet;
import com.teamTensors.planetExploreservice.repository.PlanetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanetService {

    private final PlanetRepository planetRepository;

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
        List<Planet> planets = planetRepository.findAll();

        return planets.stream().map(this::mapToPlanetResponse).toList();
    }

    private PlanetResponse mapToPlanetResponse(Planet planet) {
        return PlanetResponse.builder()
                .id(planet.getId())
                .name(planet.getName())
                .description(planet.getDescription())
                .distanceFromEarth(planet.getDistanceFromEarth())
                .imageUrls(planet.getImageUrls())
                .build();
    }
}
