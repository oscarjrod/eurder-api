package com.oscarjrod.eurderapi.items.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ItemMapperTest {

    private Item item;
    private ItemDto itemDto;

    @Autowired
    private ItemMapper itemMapper;

    @BeforeEach
    void setUp() {
        item = Item.createItem("Nintendo Switch", "4K OLED Model", 500.0, 10);
        itemDto = ItemDto.createItemDto(item);
    }

    @Test
    void testItemToItemDto() {
        ItemDto convertedItemDto = itemMapper.toDTO(item);

        assertEquals(itemDto.getId(), convertedItemDto.getId());
        assertEquals(itemDto.getName(), convertedItemDto.getName());
        assertEquals(itemDto.getDescription(), convertedItemDto.getDescription());
        assertEquals(itemDto.getPrice(), convertedItemDto.getPrice());
        assertEquals(itemDto.getStock(), convertedItemDto.getStock());
    }

    @Test
    void testItemDtoToItem() {
        Item convertedItem = itemMapper.toItem(itemDto);

        assertEquals(item.getId(), convertedItem.getId());
        assertEquals(item.getName(), convertedItem.getName());
        assertEquals(item.getDescription(), convertedItem.getDescription());
        assertEquals(item.getPrice(), convertedItem.getPrice());
        assertEquals(item.getStock(), convertedItem.getStock());
    }

}