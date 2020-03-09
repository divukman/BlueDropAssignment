package com.bluedrop.assignment.services;

import com.bluedrop.assignment.entities.OrderProduct;
import com.bluedrop.assignment.entities.State;
import com.bluedrop.assignment.repositories.OrderProductRepository;
import com.bluedrop.assignment.repositories.OrderRepository;
import com.bluedrop.assignment.web.mappers.OrderMapper;
import com.bluedrop.assignment.web.models.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDto> listAllOrders(State productState) {
        return orderRepository.findAllByState(productState).stream().map(orderMapper::orderToOrderDto).collect(Collectors.toList());
    }
}
