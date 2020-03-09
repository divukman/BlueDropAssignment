package com.bluedrop.assignment.web.controllers;

import com.bluedrop.assignment.entities.State;
import com.bluedrop.assignment.services.OrderService;
import com.bluedrop.assignment.web.models.OrderDetailsDto;
import com.bluedrop.assignment.web.models.OrderDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/order")
@RestController
@RequiredArgsConstructor
@Api(tags = "Order")
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(value = "This endpoint is used to get the all orders. Does not use pagination.")
    @GetMapping("/orders")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return new ResponseEntity<>(orderService.listAllOrders(State.ACTIVE), HttpStatus.OK);
    }

    @ApiOperation(value = "This endpoint is used to create a new order")
    @PostMapping()
    public ResponseEntity saveNewOrder(@RequestBody @Validated OrderDto orderDto) {
        return new ResponseEntity(orderService.saveNewOrder(orderDto), HttpStatus.CREATED);
    }
}
