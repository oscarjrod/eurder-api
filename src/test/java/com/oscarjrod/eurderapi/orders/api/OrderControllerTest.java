package com.oscarjrod.eurderapi.orders.api;

import com.oscarjrod.eurderapi.customers.domain.ContactDetails;
import com.oscarjrod.eurderapi.customers.domain.Customer;
import com.oscarjrod.eurderapi.items.domain.Item;
import com.oscarjrod.eurderapi.orders.domain.ItemGroup;
import com.oscarjrod.eurderapi.orders.domain.ItemGroupMapper;
import com.oscarjrod.eurderapi.orders.domain.Order;
import com.oscarjrod.eurderapi.orders.domain.OrderDto;
import com.oscarjrod.eurderapi.orders.service.OrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @Mock
    private OrderService orderService;

    @Mock
    private ItemGroupMapper itemGroupMapper;

    @InjectMocks
    private OrderController orderController;

    private Order order;
    private OrderDto orderDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        order = createOrder();
        orderDto = createOrderDto();
    }

    @Test
    void testCreateOrder() {
        when(orderService.createOrder(orderDto)).thenReturn(orderDto);

        ResponseEntity<?> responseEntity =
                orderController.createOrder(orderDto,
                        new BeanPropertyBindingResult(orderDto, "orderDto"));
        BigDecimal result = (BigDecimal) responseEntity.getBody();

        Assertions.assertEquals(order.getTotalPrice(), result);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(orderService, times(1)).createOrder(orderDto);
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