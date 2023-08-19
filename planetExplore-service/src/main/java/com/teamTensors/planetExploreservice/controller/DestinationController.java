package com.teamTensors.planetExploreservice.controller;

import com.teamTensors.planetExploreservice.dto.DestinationListDto;
import com.teamTensors.planetExploreservice.model.Destination;
import com.teamTensors.planetExploreservice.repository.DestinationRepository;
import com.teamTensors.planetExploreservice.service.DestinationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/destination")
public class DestinationController {
    @Autowired
    DestinationService destinationService;

    @PostMapping
    public ResponseEntity<Destination> createDestination(@RequestBody Destination destination){
        return destinationService.save(destination);
    }

    @GetMapping
    public ResponseEntity<List<Destination>> getAllDestinations(){
        return destinationService.getAll();
    }

    @GetMapping("/published")
    public ResponseEntity<List<Destination>> getValidDestinations(){
        return destinationService.getPublished();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Destination> getDestinationById(@PathVariable("id") String id){
        return destinationService.getDestinationById(id);
    }

    @GetMapping("/list")
    public ResponseEntity<List<DestinationListDto>> getDestinationList(){
        return destinationService.getDestinationList();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Destination> updateDestination(@PathVariable("id") String id, @RequestBody Destination destination) {
        return destinationService.updateDestination(id,destination);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteDestination(@PathVariable("id") String id) {
        return destinationService.deleteById(id);
    }
}
