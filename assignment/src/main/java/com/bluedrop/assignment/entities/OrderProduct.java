package com.bluedrop.assignment.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@EqualsAndHashCode
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderProduct {

    @EmbeddedId
    OrderProductKey id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId("order_id")
    @JoinColumn(name = "order_id")
    Order order;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @MapsId("product_id")
    @JoinColumn(name = "product_id")
    Product product;

    Integer quantity;
}
