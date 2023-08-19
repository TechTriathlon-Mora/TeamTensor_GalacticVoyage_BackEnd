package com.teamTensors.planetExploreservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class PlanetRequest {

    private String name;
    private String description;
    private BigDecimal distanceFromEarth;
    private List<String> imageUrls;
}
