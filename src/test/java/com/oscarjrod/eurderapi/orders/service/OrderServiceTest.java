package com.oscarjrod.eurderapi.orders.service;

import com.oscarjrod.eurderapi.customers.domain.ContactDetails;
import com.oscarjrod.eurderapi.customers.domain.Customer;
import com.oscarjrod.eurderapi.items.domain.Item;
import com.oscarjrod.eurderapi.orders.domain.*;
import com.oscarjrod.eurderapi.orders.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private ItemGroupMapper itemGroupMapper;

    @InjectMocks
    private OrderService orderService;

    private OrderDto orderDto;
    private Order order;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        order = createOrder();
        orderDto = createOrderDto();
    }

    @Test
    void testCreateOrder() {
        when(orderMapper.toOrder(orderDto)).thenReturn(order);
        when(orderRepository.save(order)).thenReturn(order);
        when(orderMapper.toDTO(order)).thenReturn(orderDto);

        OrderDto result = orderService.createOrder(orderDto);

        assertEquals(orderDto, result);
        verify(orderMapper).toOrder(orderDto);
        verify(orderRepository).save(order);
        verify(orderMapper).toDTO(order);
    }

    private OrderDto createOrderDto() {
        return OrderDto.createOrderDto(order.getId(), order.getCustomer(),
                order.getItemGroups().stream().map(itemGroupMapper::toDTO).collect(Collectors.toList()),
                order.getOrderDate(), order.getTotalPrice());
    }

    private Order createOrder() {
        ContactDetails contactDetails = createContactDetails();
        Customer customer = createCustomer(contactDetails);
        List<ItemGroup> itemGroups = createItemGroups();
        return Order.createOrder(customer, itemGroups);
    }

    private List<ItemGroup> createItemGroups() {
        List<ItemGroup> itemGroups = new ArrayList<>();
        itemGroups.add(createItemGroup(createItem()));
        itemGroups.add(createItemGroup(createItem()));

        return itemGroups;
    }

    private ItemGroup createItemGroup(Item item) {
        return ItemGroup.createItemGroup(
                order,
                item,
                5
        );
    }

    private Item createItem() {
        return Item.createItem("Nintendo Switch", "4K OLED Model",
                new BigDecimal("500.0"), 10);
    }

    private Customer createCustomer(ContactDetails contactDetails) {
        return Customer.createCustomer("John", "Doe", contactDetails);
    }

    private ContactDetails createContactDetails() {
        return ContactDetails.createContactDetails(
                "test@example.com", "123 Test Street", "555-1234");
    }

}