package com.teamTensors.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer extends User{

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="passenger_id", referencedColumnName = "passengerId")
    private Passenger passenger;

}
