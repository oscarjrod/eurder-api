package com.oscarjrod.eurderapi.items.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ItemDto {

    private Long id;
    @NotBlank(message = "Name cannot be empty!")
    private String name;
    @NotBlank(message = "Description cannot be empty!")
    private String description;
    @NotNull(message = "Price cannot be empty!")
    @DecimalMin(value = "0.01", message = "Price must be greater than or at least equal to 0.01!")
    private BigDecimal price;
    @NotNull(message = "Stock cannot be empty!")
    @Min(value = 0, message = "Stock must be greater than or equal to 0!")
    private long stock;

    public static ItemDto createItemDto(Item item) {
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setDescription(item.getDescription());
        dto.setPrice(item.getPrice());
        dto.setStock(item.getStock());
        return dto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getStock() {
        return stock;
    }

    public void setStock(long stock) {
        this.stock = stock;
    }

}
