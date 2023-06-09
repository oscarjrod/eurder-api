package com.oscarjrod.eurderapi.orders.domain;

import com.oscarjrod.eurderapi.items.domain.Item;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemGroupDto {

    private Long id;
    private Order order;
    @NotNull(message = "Item cannot be empty!")
    @Valid
    private Item item;
    @NotNull(message = "Amount cannot be empty!")
    @Min(value = 0, message = "Amount must be greater than or equal to 0!")
    private long amount;
    @NotNull(message = "Shipping date cannot be empty!")
    @FutureOrPresent(message = "Shipping date must be in the present or future!")
    private LocalDate shippingDate;
    @NotNull(message = "Current price cannot be empty!")
    @DecimalMin(value = "0.01", message = "Current price must be greater than or at least equal to 0.01!")
    private BigDecimal currentPrice;

    public static ItemGroupDto createItemGroupDto(Long id, Order orderId, Item itemId,
                                                  long amount, LocalDate shippingDate, BigDecimal currentPrice) {
        ItemGroupDto itemGroupDto = new ItemGroupDto();
        itemGroupDto.setId(id);
        itemGroupDto.setOrder(orderId);
        itemGroupDto.setItem(itemId);
        itemGroupDto.setAmount(amount);
        itemGroupDto.setShippingDate(shippingDate);
        itemGroupDto.setCurrentPrice(currentPrice);
        return itemGroupDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public void setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
    }

    public BigDecimal getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    @Override
    public String toString() {
        return "ItemGroup {" +
                ", order = " + order +
                ", item = " + item +
                ", amount = " + amount +
                ", shippingDate = " + shippingDate +
                ", currentPrice = " + currentPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemGroupDto that)) return false;

        if (getAmount() != that.getAmount()) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getOrder() != null ? !getOrder().equals(that.getOrder()) : that.getOrder() != null) return false;
        if (getItem() != null ? !getItem().equals(that.getItem()) : that.getItem() != null) return false;
        if (getShippingDate() != null ? !getShippingDate().equals(that.getShippingDate()) : that.getShippingDate() != null)
            return false;
        return getCurrentPrice() != null ? getCurrentPrice().equals(that.getCurrentPrice()) : that.getCurrentPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getOrder() != null ? getOrder().hashCode() : 0);
        result = 31 * result + (getItem() != null ? getItem().hashCode() : 0);
        result = 31 * result + (int) (getAmount() ^ (getAmount() >>> 32));
        result = 31 * result + (getShippingDate() != null ? getShippingDate().hashCode() : 0);
        result = 31 * result + (getCurrentPrice() != null ? getCurrentPrice().hashCode() : 0);
        return result;
    }

}

