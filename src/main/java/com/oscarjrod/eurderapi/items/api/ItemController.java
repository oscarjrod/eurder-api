package com.oscarjrod.eurderapi.items.api;

import com.oscarjrod.eurderapi.items.domain.ItemDto;
import com.oscarjrod.eurderapi.items.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json", produces = "application/json")
    public Long createItem(@RequestBody ItemDto itemDto) {
        return itemService.createItem(itemDto).getId();
    }

}
