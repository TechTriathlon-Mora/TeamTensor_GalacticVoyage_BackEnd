package com.teamTensors.planetExploreservice.service;

import com.teamTensors.planetExploreservice.dto.DestinationListDto;
import com.teamTensors.planetExploreservice.model.Destination;
import com.teamTensors.planetExploreservice.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DestinationService {

    @Autowired
    DestinationRepository destinationRepository;

    public ResponseEntity<Destination> save(Destination destination){
        try {
            Destination _destination = destinationRepository.save(new Destination(destination.getGalaxyName(),destination.getPlanetName(),destination.getAirportName(),false));
            return new ResponseEntity<>(_destination, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     public ResponseEntity<List<Destination>> getAll(){
         try {
             List<Destination> destinations = new ArrayList<Destination>();

             destinationRepository.findAll().forEach(destinations::add);

             if(destinations.isEmpty()){
                 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
             }
             return new ResponseEntity<>(destinations, HttpStatus.OK);
         } catch(Exception e) {
             return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
         }
     }

     public ResponseEntity<List<Destination>> getPublished() {
        try{
            List<Destination> destinations = destinationRepository.findByPublished(true);
            if(destinations.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(destinations, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

     }

    public ResponseEntity<Destination> getDestinationById(String id) {
        Optional<Destination> data = destinationRepository.findById(id);

        if (data.isPresent()) {
            return new ResponseEntity<>(data.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<List<DestinationListDto>> getDestinationList() {
        try {
            List<Destination> destinations = destinationRepository.findByPublished(true);
            if(destinations.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            List<DestinationListDto> listDTO = destinations.stream().map( destination -> DestinationListDto.builder()
                    .id(destination.getId())
                    .destinationName(destination.getAirportName() + " - " + destination.getPlanetName()+ " , " + destination.getGalaxyName())
                    .published(destination.isPublished())
                    .build()
            ).collect(Collectors.toList());

            return ResponseEntity.ok(listDTO);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Destination> updateDestination(String id, Destination destination) {
        Optional<Destination> data = destinationRepository.findById(id);

        if(data.isPresent()){
            Destination _destination = data.get();
            _destination.setGalaxyName(destination.getGalaxyName());
            _destination.setPlanetName(destination.getPlanetName());
            _destination.setAirportName(destination.getAirportName());
            _destination.setPublished(destination.isPublished());

            return new ResponseEntity<>(destinationRepository.save(_destination), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<HttpStatus> deleteById(String id) {
        try{
            destinationRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
