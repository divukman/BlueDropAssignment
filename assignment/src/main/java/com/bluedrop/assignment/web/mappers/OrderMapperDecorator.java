package com.bluedrop.assignment.web.mappers;


import com.bluedrop.assignment.entities.Order;
import com.bluedrop.assignment.entities.OrderProduct;
import com.bluedrop.assignment.web.models.OrderDetailsDto;
import com.bluedrop.assignment.web.models.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class OrderMapperDecorator implements OrderMapper {

    private OrderMapper orderMapper;

    @Autowired
    public void setMapper(OrderMapper mapper) {
        this.orderMapper = mapper;
    }

    @Override
    public OrderDto orderToOrderDto(Order order) {
        OrderDto orderDto = this.orderMapper.orderToOrderDto(order);
        Set<OrderDetailsDto> setOrderDetailsDtos = orderProductToProductDTO(order.getOrderProducts());
        orderDto.setOrderDetailsDtos(setOrderDetailsDtos);
        return orderDto;
    }

    Set<OrderDetailsDto> orderProductToProductDTO(Set<OrderProduct> setOrderProducts) {
        if (setOrderProducts == null) {
            return null;
        }
        return setOrderProducts
                .stream()
                .map(orderProduct ->
                        OrderDetailsDto.builder().sku(orderProduct.getProduct().getSku()).quantity(orderProduct.getQuantity()).build())
                .collect(Collectors.toSet());
    }

}
