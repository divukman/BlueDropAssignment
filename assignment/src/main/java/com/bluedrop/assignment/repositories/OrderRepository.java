package com.bluedrop.assignment.repositories;

import com.bluedrop.assignment.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order findByEmail(String email);
}
