package com.bluedrop.assignment.repositories;

import com.bluedrop.assignment.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
}
