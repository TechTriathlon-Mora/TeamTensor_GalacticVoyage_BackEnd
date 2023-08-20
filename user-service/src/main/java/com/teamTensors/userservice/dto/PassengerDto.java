package com.teamTensors.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PassengerDto {
    private int passengerId;
    private String firstName;
    private String lastName;
    private String Gender;
    private Date DOB;
    private String nationality;
}
