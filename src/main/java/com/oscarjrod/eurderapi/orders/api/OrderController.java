package com.oscarjrod.eurderapi.orders.api;

import com.oscarjrod.eurderapi.orders.domain.OrderDto;
import com.oscarjrod.eurderapi.orders.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping(value = "orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public BigDecimal createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto).getTotalPrice();
    }

}
