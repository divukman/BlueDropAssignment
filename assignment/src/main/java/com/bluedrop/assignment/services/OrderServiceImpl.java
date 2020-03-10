package com.bluedrop.assignment.services;

import com.bluedrop.assignment.entities.Order;
import com.bluedrop.assignment.entities.OrderProduct;
import com.bluedrop.assignment.entities.Product;
import com.bluedrop.assignment.entities.State;
import com.bluedrop.assignment.repositories.OrderProductRepository;
import com.bluedrop.assignment.repositories.OrderRepository;
import com.bluedrop.assignment.repositories.ProductRepository;
import com.bluedrop.assignment.web.exception.MissingOrderDetailsException;
import com.bluedrop.assignment.web.exception.NotFoundException;
import com.bluedrop.assignment.web.mappers.OrderMapper;
import com.bluedrop.assignment.web.models.OrderDetailsDto;
import com.bluedrop.assignment.web.models.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrderMapper orderMapper;

    /**
     * Lists all products by state
     *
     * @param productState
     * @return
     */
    @Override
    public List<OrderDto> listAllOrders(State productState) {
        return orderRepository.findAllByState(productState)
                .stream().map(orderMapper::orderToOrderDto).collect(Collectors.toList());
    }

    /**
     * Lists all products within the datetime range provided.
     * @param productState
     * @param from
     * @param to
     * @return
     */
    @Override
    public List<OrderDto> listAllOrdersForRange(State productState, OffsetDateTime from, OffsetDateTime to) {
        return orderRepository.findAllByStateAndCreatedDateGreaterThanAndCreatedDateLessThan(productState, from, to)
                .stream().map(orderMapper::orderToOrderDto).collect(Collectors.toList());
    }

    /**
     * Saves the new product
     *
     * @param orderDto
     * @return - order dto object
     */
    @Override
    public OrderDto saveNewOrder(OrderDto orderDto) {
        final Order order = orderMapper.orderDtoToOrder(orderDto);
        order.setState(State.ACTIVE);

        // TODO: use Optional
        Set<OrderDetailsDto> orderDetailsDtos = orderDto.getOrderDetailsDtos();
        Set<OrderProduct> orderProductSet = new HashSet<>();

        if (orderDetailsDtos != null) {
            orderProductSet = orderDetailsDtos.stream().map(orderDetailDto ->
                    {
                        Product product = productRepository.findBySku(orderDetailDto.getSku())
                                .orElseThrow(() ->
                                        new NotFoundException(String.format("Product with SKU: %s not found.", orderDetailDto.getSku())));
                        OrderProduct orderProduct = OrderProduct.builder()
                                .order(order)
                                .product(product)
                                .quantity(orderDetailDto.getQuantity())
                                .build();
                        return orderProduct;
                    }
            ).collect(Collectors.toSet());
            orderProductRepository.saveAll(orderProductSet);
        } else {
            throw new MissingOrderDetailsException("OrderDetails missing from request!");
        }
        order.setOrderProducts(orderProductSet);
        return orderMapper.orderToOrderDto(order);
    }
}
