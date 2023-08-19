package com.teamTensors.tripScheduleservice.Repo;

import com.teamTensors.tripScheduleservice.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepo extends JpaRepository<Trip, Integer> {

}
