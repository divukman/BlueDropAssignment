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

@Mapper(uses = {DateMapper.class})
@DecoratedWith(OrderMapperDecorator.class)
public interface OrderMapper {
    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    OrderDto orderToOrderDto(Order order);

//    default Set<ProductDto> map(Set<OrderProduct> orderProducts) {
//        if (orderProducts == null) {
//            return null;
//        }
//        return orderProducts.stream().map(orderProduct -> PRODUCT_MAPPER.productToProductDto(orderProduct.getProduct())).collect(Collectors.toSet());
//    }

    Order orderDtoToOrder(OrderDto orderDto);
}
