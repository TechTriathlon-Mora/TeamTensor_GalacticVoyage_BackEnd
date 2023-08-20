package com.teamTensors.planetExploreservice.controller;

import com.teamTensors.planetExploreservice.model.PlanetList;
import com.teamTensors.planetExploreservice.service.PlanetListService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/planet/list")
public class PlanetListController {

    @Autowired
    private PlanetListService planetListService;

    @GetMapping
    public ResponseEntity<List<PlanetList>> getPlanetList(){
        return planetListService.getFullPlanetList();
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<PlanetList>> getByPartialName(@PathVariable("name") String name){
        return planetListService.findByPartialName(name);
    }
}
