package com.oscarjrod.eurderapi.items.api;

import com.oscarjrod.eurderapi.items.domain.Item;
import com.oscarjrod.eurderapi.items.domain.ItemDto;
import com.oscarjrod.eurderapi.items.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public class ItemControllerTest {

    private Item item;
    private ItemDto itemDto;
    private ItemService itemService;
    private ItemController itemController;

    @BeforeEach
    void setUp() {
        itemService = mock(ItemService.class);
        itemController = new ItemController(itemService);
        item = Item.createItem("Nintendo Switch", "4K OLED Model", 500.0, 10);
        itemDto = ItemDto.createItemDto(item);
    }

    @Test
    void testCreateItem() {
        when(itemService.createItem(itemDto)).thenReturn(ItemDto.createItemDto(item));

        Long result = itemController.createItem(itemDto);

        Assertions.assertEquals(item.getId(), result);
        verify(itemService, times(1)).createItem(itemDto);
    }

}
