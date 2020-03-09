package com.bluedrop.assignment.web.mappers;

import com.bluedrop.assignment.entities.Order;
import com.bluedrop.assignment.entities.OrderProduct;
import com.bluedrop.assignment.web.models.OrderDto;
import com.bluedrop.assignment.web.models.ProductDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
@DecoratedWith(OrderMapperDecorator.class)
public interface OrderMapper {
    OrderDto orderToOrderDto(Order order);
    Order orderDtoToOrder(OrderDto orderDto);
}
