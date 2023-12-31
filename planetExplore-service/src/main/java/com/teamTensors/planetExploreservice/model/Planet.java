package com.teamTensors.planetExploreservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collation = "planets")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Planet {

    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal distanceFromEarth;
    private List<String> imageUrls;

}
