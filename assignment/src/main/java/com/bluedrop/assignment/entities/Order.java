package com.bluedrop.assignment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "request")
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
    @JsonIgnore
    Set<OrderProduct> orderProducts;
}
