package com.oscarjrod.eurderapi.orders.domain;

import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderMapper {

    private final ItemGroupMapper itemGroupMapper = new ItemGroupMapper();

    // Order => DTO
    public OrderDto toDTO(Order myOrder) {
        return OrderDto.createOrderDto(
                myOrder.getId(),
                myOrder.getCustomer(),
                myOrder.getItemGroups().stream()
                        .map(itemGroupMapper::toDTO)
                        .collect(Collectors.toList()),
                myOrder.getOrderDate(),
                myOrder.getTotalPrice()
        );
    }

    // DTO => Order
    public Order toOrder(@Valid OrderDto orderDto) {
        return Order.createOrder(
                orderDto.getCustomer(),
                orderDto.getItemGroups().stream()
                        .map(itemGroupMapper::toItemGroup)
                        .collect(Collectors.toList())
        );
    }

}
