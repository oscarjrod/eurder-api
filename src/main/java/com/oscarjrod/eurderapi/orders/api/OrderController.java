package com.oscarjrod.eurderapi.orders.api;

import com.oscarjrod.eurderapi.orders.domain.OrderDto;
import com.oscarjrod.eurderapi.orders.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(e -> String.format("%s: %s", e.getField(), e.getDefaultMessage()))
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        BigDecimal totalPrice = orderService.createOrder(orderDto).getTotalPrice();
        return ResponseEntity.ok(totalPrice);
    }

}
