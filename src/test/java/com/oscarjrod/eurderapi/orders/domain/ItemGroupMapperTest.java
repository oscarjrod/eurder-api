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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ItemGroupMapperTest {

    private ItemGroup itemGroup;
    private ItemGroupDto itemGroupDto;

    @Autowired
    private ItemGroupMapper itemGroupMapper;

    @BeforeEach
    void setUp() {
        Order order = Order.createOrder(Customer.createCustomer("John", "Doe",
                        ContactDetails.createContactDetails("test@example.com", "123 Test Street", "555-1234")),
                new ArrayList<>());
        Item item = Item.createItem("Nintendo Switch", "4K OLED Model", new BigDecimal("500.0"), 10);
        itemGroup = ItemGroup.createItemGroup(order, item, 5);
        itemGroupDto = itemGroupMapper.toDTO(itemGroup);
    }

    @Test
    void testToDTO() {
        ItemGroupDto convertedDto = itemGroupMapper.toDTO(itemGroup);

        assertEquals(itemGroupDto.getId(), convertedDto.getId());
        assertEquals(itemGroupDto.getOrder(), convertedDto.getOrder());
        assertEquals(itemGroupDto.getItem(), convertedDto.getItem());
        assertEquals(itemGroupDto.getAmount(), convertedDto.getAmount());
        assertEquals(itemGroupDto.getShippingDate(), convertedDto.getShippingDate());
        assertEquals(itemGroupDto.getCurrentPrice(), convertedDto.getCurrentPrice());
    }

    @Test
    void testToItemGroup() {
        ItemGroup convertedItemGroup = itemGroupMapper.toItemGroup(itemGroupDto);

        assertEquals(itemGroup.getId(), convertedItemGroup.getId());
        assertEquals(itemGroup.getOrder(), convertedItemGroup.getOrder());
        assertEquals(itemGroup.getItem(), convertedItemGroup.getItem());
        assertEquals(itemGroup.getAmount(), convertedItemGroup.getAmount());
        assertEquals(itemGroup.getShippingDate(), convertedItemGroup.getShippingDate());
        assertEquals(itemGroup.getCurrentPrice(), convertedItemGroup.getCurrentPrice());
    }

}

