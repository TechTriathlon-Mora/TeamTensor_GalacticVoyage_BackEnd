package com.teamTensors.userservice.repo;

import com.teamTensors.userservice.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasssengerRepo extends JpaRepository<Passenger, Integer> {
}
