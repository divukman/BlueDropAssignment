package com.bluedrop.assignment.web.controllers;

import com.bluedrop.assignment.entities.Order;
import com.bluedrop.assignment.entities.State;
import com.bluedrop.assignment.services.OrderService;
import com.bluedrop.assignment.web.models.OrderDetailsDto;
import com.bluedrop.assignment.web.models.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.bluedrop.assignment.web.models.OrderDto;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    OrderService orderService;

    @Test
    void getAllOrders() throws Exception {
        when(orderService.listAllOrders(State.ACTIVE)).thenReturn(getOrderDtoList());

        mockMvc.perform(get("/api/v1/order/orders")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].orderDetails").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("[0].orderDetails[0].sku").value("SKU2"))
                .andExpect(MockMvcResultMatchers.jsonPath("[1].orderDetails[0].quantity").value(1));
    }

    @Test
    void saveNewOrder() throws Exception {
        final OrderDto orderDto = getOrderDto();
        final String strOrderDtoJson = objectMapper.writeValueAsString(orderDto);

        given(orderService.saveNewOrder(any())).willReturn(orderDto);

        mockMvc.perform(
                post("/api/v1/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(strOrderDtoJson)
        ).andExpect(status().isCreated());
    }

    private List<OrderDto> getOrderDtoList() {
        final List<OrderDto> lstOrders = new ArrayList<>(2);

        final OrderDto order1 = OrderDto.builder()
                .email("ante@gmail.com")
                .build();

        Set<OrderDetailsDto> orderDetailsDtoSet1 = new HashSet<>();

        OrderDetailsDto orderDetailsDto1 = new OrderDetailsDto();
        orderDetailsDto1.setQuantity(5);
        orderDetailsDto1.setSku("SKU1");

        OrderDetailsDto orderDetailsDto2 = new OrderDetailsDto();
        orderDetailsDto2.setQuantity(8);
        orderDetailsDto2.setSku("SKU2");

        orderDetailsDtoSet1.add(orderDetailsDto1);
        orderDetailsDtoSet1.add(orderDetailsDto2);

        order1.setOrderDetailsDtos(orderDetailsDtoSet1);

        final OrderDto order2 = OrderDto.builder()
                .email("dimitar@gmail.com")
                .build();

        Set<OrderDetailsDto> orderDetailsDtoSet2 = new HashSet<>();

        OrderDetailsDto orderDetailsDto3 = new OrderDetailsDto();
        orderDetailsDto3.setQuantity(1);
        orderDetailsDto3.setSku("SKU8");

        orderDetailsDtoSet2.add(orderDetailsDto3);
        order2.setOrderDetailsDtos(orderDetailsDtoSet2);

        lstOrders.add(order1);
        lstOrders.add(order2);

        return lstOrders;
    }

    private OrderDto getOrderDto() {
        final OrderDto orderDto = OrderDto.builder()
                .email("ante@gmail.com")
                .build();

        Set<OrderDetailsDto> orderDetailsDtoSet1 = new HashSet<>();

        OrderDetailsDto orderDetailsDto1 = new OrderDetailsDto();
        orderDetailsDto1.setQuantity(5);
        orderDetailsDto1.setSku("SKU1");

        OrderDetailsDto orderDetailsDto2 = new OrderDetailsDto();
        orderDetailsDto2.setQuantity(8);
        orderDetailsDto2.setSku("SKU2");

        orderDetailsDtoSet1.add(orderDetailsDto1);
        orderDetailsDtoSet1.add(orderDetailsDto2);

        orderDto.setOrderDetailsDtos(orderDetailsDtoSet1);
        return orderDto;
    }
}
