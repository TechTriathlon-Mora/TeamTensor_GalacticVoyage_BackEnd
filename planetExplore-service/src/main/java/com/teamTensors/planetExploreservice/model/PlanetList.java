package com.teamTensors.planetExploreservice.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "planetList")
@NoArgsConstructor
@Data
public class PlanetList {

    @Id
    private String id;
    private String planetId;
    private String planetName;

    public PlanetList(String planetId, String planetName){
        this.planetId = planetId;
        this.planetName = planetName;
    }
}
