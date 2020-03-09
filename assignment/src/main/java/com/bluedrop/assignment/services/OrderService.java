package com.bluedrop.assignment.services;

import com.bluedrop.assignment.entities.State;
import com.bluedrop.assignment.web.models.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> listAllOrders(State productState);
    OrderDto saveNewOrder(OrderDto orderDto);
}
