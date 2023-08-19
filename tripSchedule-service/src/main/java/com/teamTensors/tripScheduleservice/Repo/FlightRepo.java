package com.teamTensors.tripScheduleservice.Repo;

import com.teamTensors.tripScheduleservice.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepo extends JpaRepository<Flight, Integer> {

}
