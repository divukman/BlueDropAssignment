package com.bluedrop.assignment.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

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
public class Product {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type="org.hibernate.type.UUIDCharType")
    @Column(length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    private UUID id;

    @Version
    private Long version;

    /**
     * Date the entity was last created.
     * We are forcing the save to be in the UTC timezone.
     */
    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDate;

    /**
     * Date the entity was last modified.
     * We are forcing the save to be in the UTC timezone.
     */
    @UpdateTimestamp
    private Timestamp lastModifiedDate;

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
