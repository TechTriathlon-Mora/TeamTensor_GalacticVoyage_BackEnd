package com.teamTensors.bookingservice.repository;

import com.teamTensors.bookingservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
