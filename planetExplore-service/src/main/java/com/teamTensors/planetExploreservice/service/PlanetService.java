package com.teamTensors.planetExploreservice.service;

import com.teamTensors.planetExploreservice.dto.PlanetRequest;
import com.teamTensors.planetExploreservice.dto.PlanetResponse;
import com.teamTensors.planetExploreservice.model.Destination;
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
import java.util.stream.Collectors;

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

//    public List<PlanetResponse> getAllPlanets() {
//        System.out.println("planet is asked");
//        List<Planet> planets = planetRepository.findAll();
//
//        //return planets.stream().map(this::mapToPlanetResponse).toList();
//        return planets.stream().map(this::mapToPlanetResponse).toList();
//    }
//
//    private PlanetResponse mapToPlanetResponse(Planet planet) {
//        List<String> links = new ArrayList<>(planet.getImageUrls());
//        log.info("Planet {} is returned", planet.getId());
//        return PlanetResponse.builder()
//                .id(planet.getId())
//                .name(planet.getName())
//                .description(planet.getDescription())
//                .distanceFromEarth(planet.getDistanceFromEarth())
//                .imageUrls(links)
//                .build();
//    }

    public ResponseEntity<List<Planet>> getAllPlanets() {

        try {
            List<Planet> planets = new ArrayList<Planet>();

            planetRepository.findAll().forEach(planets::add);

            if(planets.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(planets, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    public List<Planet> getPlanets(){
        return planetRepository.findAll();
    }

    public ResponseEntity<Planet> getPlanetById(String id){
        Optional<Planet> data = planetRepository.findById(id);

        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<Planet>> getPlanetByName(String name) {
//        try {
//            List<Planet> planets = new ArrayList<>();
//            if(name == null){
//                planetRepository.findAll().forEach(planets::add);
//            }
//            else{
//                planetRepository.getByName(name).forEach(planets::add);
//            }
//            if (planets.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(planets, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }

        List<Planet> planets = planetRepository.findByNameContaining(name);
        if (planets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(planets, HttpStatus.OK);
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
