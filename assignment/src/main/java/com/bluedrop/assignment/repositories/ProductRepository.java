package com.bluedrop.assignment.repositories;

import com.bluedrop.assignment.entities.Product;
import com.bluedrop.assignment.entities.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Page<Product> findAllByName(String name, Pageable pageable);

    Page<Product> findAllByNameAndState(String name, State state, Pageable pageable);

    Page<Product> findAllByState(State state, Pageable pageable);

    List<Product> findAllByState(State state);

    Optional<Product> findBySku(String sku);
}
