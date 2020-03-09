package com.bluedrop.assignment.repositories;

import com.bluedrop.assignment.entities.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID> {
}
