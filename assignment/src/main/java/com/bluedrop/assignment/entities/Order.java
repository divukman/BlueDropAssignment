package com.bluedrop.assignment.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Order entity.
 *
 * @author Ante
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="request")
public class Order extends BaseEntity {

    @Builder
    public Order(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, State state, String email) {
        super(id, version, createdDate, lastModifiedDate, state);
        this.email = email;
    }

    /**
     * Email of a buyer.
     */
    private String email;

    @OneToMany(mappedBy = "order")
    Set<OrderProduct> orderProducts;
}
