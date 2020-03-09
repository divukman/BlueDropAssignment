package com.bluedrop.assignment.repositories;

import com.bluedrop.assignment.entities.Order;
import com.bluedrop.assignment.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Order findByEmail(String email);
    List<Order> findAllByState(State state);
}
