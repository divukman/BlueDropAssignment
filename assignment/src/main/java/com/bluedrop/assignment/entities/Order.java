package com.bluedrop.assignment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_") // added _ in table name since 'order' is special SQL word
@Audited
public class Order extends BaseEntity {

    @Builder
    public Order(UUID id, Long version, OffsetDateTime createdDate, OffsetDateTime lastModifiedDate, State state, String email) {
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
