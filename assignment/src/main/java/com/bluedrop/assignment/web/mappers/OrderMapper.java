package com.bluedrop.assignment.web.mappers;

import com.bluedrop.assignment.entities.Order;
import com.bluedrop.assignment.web.models.OrderDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface OrderMapper {
    OrderDto orderToOrderDto(Order order);
    Order orderDtoToOrder(OrderDto orderDto);
}
