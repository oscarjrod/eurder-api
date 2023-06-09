package com.oscarjrod.eurderapi.orders.service;

import com.oscarjrod.eurderapi.orders.domain.Order;
import com.oscarjrod.eurderapi.orders.domain.OrderDto;
import com.oscarjrod.eurderapi.orders.domain.OrderMapper;
import com.oscarjrod.eurderapi.orders.repository.OrderRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository,
                        OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public OrderDto createOrder(@Valid OrderDto orderDto) {
        Order createdOrder = orderMapper.toOrder(orderDto);
        Order savedOrder = orderRepository.save(createdOrder);

        return orderMapper.toDTO(savedOrder);
    }

}
