package com.oscarjrod.eurderapi.items.api;

import com.oscarjrod.eurderapi.items.domain.Item;
import com.oscarjrod.eurderapi.items.domain.ItemDto;
import com.oscarjrod.eurderapi.items.service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;

import java.math.BigDecimal;

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
        item = Item.createItem("Nintendo Switch", "4K OLED Model", new BigDecimal("500.0"), 10);
        itemDto = ItemDto.createItemDto(item);
    }

    @Test
    void testCreateItem() {
        when(itemService.createItem(itemDto)).thenReturn(ItemDto.createItemDto(item));

        ResponseEntity<?> responseEntity =
                itemController.createItem(itemDto,
                        new BeanPropertyBindingResult(itemDto, "itemDto"));
        Long result = (Long) responseEntity.getBody();

        Assertions.assertEquals(item.getId(), result);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        verify(itemService, times(1)).createItem(itemDto);
    }

}
