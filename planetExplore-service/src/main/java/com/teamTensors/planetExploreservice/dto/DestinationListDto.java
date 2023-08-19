package com.teamTensors.planetExploreservice.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DestinationListDto {
    private String id;
    private String destinationName;
    private boolean published;
}
