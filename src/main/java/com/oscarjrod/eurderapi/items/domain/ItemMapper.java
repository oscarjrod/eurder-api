package com.oscarjrod.eurderapi.items.domain;

import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    // Item => DTO
    public ItemDto toDTO(Item myItem) {
        return ItemDto.createItemDto(myItem);
    }

    // DTO => Item
    public Item toItem(@Valid ItemDto itemDto) {
        return Item.createItem(
                itemDto.getName(),
                itemDto.getDescription(),
                itemDto.getPrice(),
                itemDto.getStock()
                );
    }

}
