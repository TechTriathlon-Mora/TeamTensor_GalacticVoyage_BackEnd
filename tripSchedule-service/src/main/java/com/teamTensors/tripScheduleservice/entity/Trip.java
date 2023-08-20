package com.teamTensors.tripScheduleservice.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trip {
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="flight_id", referencedColumnName = "flightId")
    private Flight flight;

    @ElementCollection
    private List<Integer> availableSeats;

}
