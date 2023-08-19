package com.teamTensors.tripScheduleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TripDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripId;
    private String ticketName;
    private Time departureTime;
    private Time returnTime;
    private Date departureDate;
    private Date returnDate;
    private String departureLocation;
    private String returnLocation;

    @ElementCollection
    private List<Integer> availableSeats;
}
