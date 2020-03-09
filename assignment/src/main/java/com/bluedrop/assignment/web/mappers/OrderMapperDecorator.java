package com.bluedrop.assignment.web.mappers;


import com.bluedrop.assignment.entities.Order;
import com.bluedrop.assignment.entities.OrderProduct;
import com.bluedrop.assignment.services.ProductService;
import com.bluedrop.assignment.web.models.OrderDetailsDto;
import com.bluedrop.assignment.web.models.OrderDto;
import com.bluedrop.assignment.web.models.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class OrderMapperDecorator implements OrderMapper {

    private OrderMapper orderMapper;
    private ProductMapper productMapper;

    @Autowired
    public void setMapper(OrderMapper mapper, ProductMapper productMapper) {
        this.orderMapper = mapper;
        this.productMapper = productMapper;
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
