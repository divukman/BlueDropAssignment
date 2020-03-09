package com.bluedrop.assignment.web.mappers;


import com.bluedrop.assignment.entities.Order;
import com.bluedrop.assignment.entities.OrderProduct;
import com.bluedrop.assignment.services.ProductService;
import com.bluedrop.assignment.web.models.OrderDto;
import com.bluedrop.assignment.web.models.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

public abstract class OrderMapperDecorator implements OrderMapper {

    OrderMapper orderMapper;
    ProductMapper productMapper;

    @Autowired
    public void setMapper(OrderMapper mapper, ProductMapper productMapper) {
        this.orderMapper = mapper;
        this.productMapper = productMapper;
    }

    @Autowired
    ProductService productService;

    @Override
    public OrderDto orderToOrderDto(Order order) {
      OrderDto orderDto = this.orderMapper.orderToOrderDto(order);

        Set<ProductDto> setProductDtos=   orderProductToProductDTO(order.getOrderProducts());

      orderDto.setProductDtos(setProductDtos);
      return  orderDto;
    }

    Set<ProductDto> orderProductToProductDTO(Set<OrderProduct> setOrderProducts) {
        return setOrderProducts
                .stream()
                .map(orderProduct -> this.productMapper.productToProductDto(orderProduct.getProduct())).collect(Collectors.toSet());
    }

}
