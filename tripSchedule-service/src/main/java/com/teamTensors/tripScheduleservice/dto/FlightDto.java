package com.teamTensors.tripScheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FlightDto {
    private int flightId;
    private String flightName;
    private String capacity;
}
