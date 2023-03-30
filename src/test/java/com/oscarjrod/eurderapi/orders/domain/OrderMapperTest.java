package com.oscarjrod.eurderapi.orders.domain;

import com.oscarjrod.eurderapi.customers.domain.ContactDetails;
import com.oscarjrod.eurderapi.customers.domain.Customer;
import com.oscarjrod.eurderapi.items.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class OrderMapperTest {

    private Order order;
    private OrderDto orderDto;

    @Autowired
    private OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        List<ItemGroup> itemGroups = new ArrayList<>();
        ItemGroup itemGroup = ItemGroup.createItemGroup(
                order,
                Item.createItem("Nintendo Switch", "4K OLED Model",
                        new BigDecimal("500.0"), 10),
                5);
        itemGroups.add(itemGroup);

        ContactDetails contactDetails = ContactDetails.createContactDetails(
                "test@example.com", "123 Test Street", "555-1234");
        Customer customer = Customer.createCustomer("John", "Doe", contactDetails);


        order = Order.createOrder(customer, itemGroups);
        orderDto = orderMapper.toDTO(order);
    }

    @Test
    void testOrderToOrderDto() {
        OrderDto convertedOrderDto = orderMapper.toDTO(order);

        assertEquals(orderDto.getId(), convertedOrderDto.getId());
        assertEquals(orderDto.getCustomer(), convertedOrderDto.getCustomer());
        assertEquals(orderDto.getItemGroups(), convertedOrderDto.getItemGroups());
        assertEquals(orderDto.getOrderDate(), convertedOrderDto.getOrderDate());
        assertEquals(orderDto.getTotalPrice(), convertedOrderDto.getTotalPrice());
    }

    @Test
    void testOrderDtoToOrder() {
        Order convertedOrder = orderMapper.toOrder(orderDto);

        assertEquals(order.getId(), convertedOrder.getId());
        assertEquals(order.getCustomer(), convertedOrder.getCustomer());
        assertEquals(order.getItemGroups(), convertedOrder.getItemGroups());
        assertEquals(order.getOrderDate(), convertedOrder.getOrderDate());
        assertEquals(order.getTotalPrice(), convertedOrder.getTotalPrice());
    }

}