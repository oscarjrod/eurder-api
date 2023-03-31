package com.oscarjrod.eurderapi.orders.domain;

import com.oscarjrod.eurderapi.items.domain.Item;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class ItemGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private long amount;
    private LocalDate shippingDate;
    private BigDecimal currentPrice;

    public ItemGroup() {
        this.shippingDate = null;
    }

    public static ItemGroup createItemGroup(Order order, Item item, long amount) {
        ItemGroup itemGroup = new ItemGroup();
        itemGroup.setOrder(order);
        itemGroup.setItem(item);
        itemGroup.setAmount(amount);
        itemGroup.setCurrentPrice(item.getPrice());
        itemGroup.setShippingDate(calculateShippingDate(item, amount));
        return itemGroup;
    }

    public Long getId() {
        return id;
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
        this.currentPrice = item.getPrice();
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

    public BigDecimal getItemGroupTotalPrice() {
        return currentPrice.multiply(BigDecimal.valueOf(amount));
    }

    private static LocalDate calculateShippingDate(Item item, long amount) {
        if (item != null && item.getStock() >= amount) {
            return LocalDate.now().plusDays(1);
        } else {
            return LocalDate.now().plusDays(7);
        }
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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ItemGroup other)) return false;
        return Objects.equals(id, other.id) &&
                Objects.equals(amount, other.amount) &&
                Objects.equals(item, other.item);
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
