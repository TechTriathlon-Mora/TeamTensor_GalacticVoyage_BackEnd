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
    private Time depatureTime;
    private Time returnTime;
    private Date depatureDate;
    private Date returnDate;
    private String depatureLocation;
    private String returnLocation;

    @ElementCollection
    private List<Integer> availableSeats;

}
