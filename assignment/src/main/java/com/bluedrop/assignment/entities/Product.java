package com.bluedrop.assignment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

/**
 * Product entity.
 *
 * @author Dimitar
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseEntity {

    @Builder // hack to work with super class, try with @SuperBuilder
    public Product(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, State state, String sku, String name, BigDecimal price) {
        super(id, version, createdDate, lastModifiedDate, state);

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

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    Set<OrderProduct> orderProducts;
}
