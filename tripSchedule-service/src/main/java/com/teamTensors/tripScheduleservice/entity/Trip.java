package com.teamTensors.tripScheduleservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int tripId;
    private String ticketName;
    private Time returnTime;
    private Time depatureTime;
    private Date returnDate;
    private Date departureDate;
    private String returnLocation;
    private String departureLocation;

    @ElementCollection
    private List<Integer> availableSeats;

}
