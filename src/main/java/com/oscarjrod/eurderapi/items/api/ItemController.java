package com.oscarjrod.eurderapi.items.api;

import com.oscarjrod.eurderapi.items.domain.ItemDto;
import com.oscarjrod.eurderapi.items.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createItem(@Valid @RequestBody ItemDto itemDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors().stream()
                    .map(e -> String.format("%s: %s", e.getField(), e.getDefaultMessage()))
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        Long itemId = itemService.createItem(itemDto).getId();
        return ResponseEntity.ok(itemId);
    }

}
