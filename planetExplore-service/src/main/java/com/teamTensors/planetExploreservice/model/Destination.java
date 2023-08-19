package com.teamTensors.planetExploreservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "destinations")
@NoArgsConstructor
@Data
public class Destination {
    @Id
    private String id;

    private String galaxyName;
    private String planetName;
    private String airportName;
    private boolean published;

    public Destination(String galaxyName, String planetName, String airportName, boolean published ){
        this.galaxyName = galaxyName;
        this.planetName = planetName;
        this.airportName = airportName;
        this.published = published;
    }

}
