package com.oscarjrod.eurderapi.items.service;

import com.oscarjrod.eurderapi.items.domain.Item;
import com.oscarjrod.eurderapi.items.domain.ItemDto;
import com.oscarjrod.eurderapi.items.domain.ItemMapper;
import com.oscarjrod.eurderapi.items.repository.ItemRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository,
                       ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDto createItem(@Valid ItemDto itemDto) {
        Item createdItem = itemMapper.toItem(itemDto);
        Item savedItem = itemRepository.save(createdItem);

        return itemMapper.toDTO(savedItem);
    }

}
