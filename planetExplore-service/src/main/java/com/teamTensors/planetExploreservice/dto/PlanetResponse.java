package com.teamTensors.planetExploreservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

import java.math.BigDecimal;

@Data
@Builder
public class PlanetResponse {

    private String id;
    private String name;
    private String description;
    private BigDecimal distanceFromEarth;
    private List<String> imageUrls;
}
