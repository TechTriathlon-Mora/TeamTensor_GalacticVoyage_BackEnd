package com.teamTensors.tripScheduleservice.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int flightId;
    private String flightName;
    private String capacity;

    @OneToOne(mappedBy = "flight")
    private Trip trip;

}
