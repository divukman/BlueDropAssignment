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

    //@EmbeddedId
    //OrderProductKey id;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    //@MapsId("order_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    Order order;

    //@MapsId("product_id")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    Product product;

    Integer quantity;
}
