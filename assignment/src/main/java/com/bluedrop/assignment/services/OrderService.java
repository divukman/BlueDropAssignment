package com.bluedrop.assignment.services;

import com.bluedrop.assignment.entities.State;
import com.bluedrop.assignment.web.models.OrderDto;

import java.time.OffsetDateTime;
import java.util.List;

public interface OrderService {
    List<OrderDto> listAllOrders(State productState);
    List<OrderDto> listAllOrdersForRange(State productState, OffsetDateTime from, OffsetDateTime to);
    OrderDto saveNewOrder(OrderDto orderDto);
}
