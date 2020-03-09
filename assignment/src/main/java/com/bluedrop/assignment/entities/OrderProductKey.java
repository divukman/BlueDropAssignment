package com.bluedrop.assignment.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
public class OrderProductKey implements Serializable {

    @Column(name = "product_id", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    UUID productId;

    @Column(name = "order_id", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    UUID orderId;
}
