package com.oscarjrod.eurderapi.items.service;

import com.oscarjrod.eurderapi.items.domain.Item;
import com.oscarjrod.eurderapi.items.domain.ItemDto;
import com.oscarjrod.eurderapi.items.domain.ItemMapper;
import com.oscarjrod.eurderapi.items.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private ItemMapper itemMapper;

    @InjectMocks
    private ItemService itemService;

    private ItemDto itemDto;
    private Item item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        item = Item.createItem("Nintendo Switch", "4K OLED Model", new BigDecimal("500.0"), 10);
        itemDto = ItemDto.createItemDto(item);
    }

    @Test
    void testCreateItem() {
        when(itemMapper.toItem(itemDto)).thenReturn(item);
        when(itemRepository.save(item)).thenReturn(item);
        when(itemMapper.toDTO(item)).thenReturn(itemDto);

        ItemDto result = itemService.createItem(itemDto);

        assertEquals(itemDto, result);
        verify(itemMapper).toItem(itemDto);
        verify(itemRepository).save(item);
        verify(itemMapper).toDTO(item);
    }

}