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
    private Float ticketPrice;
    private Time returnTime;
    private Time depatureTime;
    private Date returnDate;
    private Date departureDate;
    private String returnLocation;
    private String departureLocation;
    private String test;


    @ElementCollection
    private List<Integer> availableSeats;
}
