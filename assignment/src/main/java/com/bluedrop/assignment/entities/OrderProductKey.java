package com.bluedrop.assignment.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import java.util.UUID;

@Embeddable
@EqualsAndHashCode
@Setter
@Getter
@AllArgsConstructor
public class OrderProductKey implements Serializable {

//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    @Type(type="org.hibernate.type.UUIDCharType")
 //   @GeneratedValue
    @Column(name = "product_id", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    UUID productId;

 //   @GeneratedValue
    @Column(name = "order_id", length = 36, columnDefinition = "varchar(36)", updatable = false, nullable = false)
    UUID orderId;
}
