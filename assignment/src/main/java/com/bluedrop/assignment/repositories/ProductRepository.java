package com.bluedrop.assignment.repositories;

import com.bluedrop.assignment.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findAllByName(String name, Pageable pageable);

    Optional<Product> findBySku(String sku);
}
