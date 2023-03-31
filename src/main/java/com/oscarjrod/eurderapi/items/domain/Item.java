package com.oscarjrod.eurderapi.items.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public static Item createItem(String name, String description, BigDecimal price, long stock) {
        Item item = new Item();
        item.setName(name);
        item.setDescription(description);
        item.setPrice(price);
        item.setStock(stock);
        return item;
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

    @Override
    public String toString() {
        return "Item {" +
                ", name = " + name +
                ", description = " + description +
                ", price = " + price +
                ", stock = " + stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;

        if (getStock() != item.getStock()) return false;
        if (getId() != null ? !getId().equals(item.getId()) : item.getId() != null) return false;
        if (getName() != null ? !getName().equals(item.getName()) : item.getName() != null) return false;
        if (getDescription() != null ? !getDescription().equals(item.getDescription()) : item.getDescription() != null)
            return false;
        return getPrice() != null ? getPrice().equals(item.getPrice()) : item.getPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        result = 31 * result + (int) (getStock() ^ (getStock() >>> 32));
        return result;
    }

}
