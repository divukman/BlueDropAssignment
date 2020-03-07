package com.bluedrop.assignment.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * Product entity.
 *
 * @author Dimitar
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product extends BaseEntity {

    @Builder // hack to work with super class, use @SuperBuilder in Lombok 1.18
    public Product(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String sku, String name, BigDecimal price) {
        super(id, version, createdDate, lastModifiedDate);

        this.sku = sku;
        this.name = name;
        this.price = price;
    }

    /**
     * SKU Number -> stock keeping unit. A unique product number.
     */
    @Column(unique = true)
    private String sku;

    /**
     * Product name.
     */
    private String name;

    /**
     * Product price.
     */
    private BigDecimal price;
}
