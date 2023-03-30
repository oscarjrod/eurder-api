package com.oscarjrod.eurderapi.orders.domain;

import com.oscarjrod.eurderapi.items.domain.Item;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemGroup itemGroup)) return false;

        if (getAmount() != itemGroup.getAmount()) return false;
        if (getId() != null ? !getId().equals(itemGroup.getId()) : itemGroup.getId() != null) return false;
        if (getOrder() != null ? !getOrder().equals(itemGroup.getOrder()) : itemGroup.getOrder() != null) return false;
        if (getItem() != null ? !getItem().equals(itemGroup.getItem()) : itemGroup.getItem() != null) return false;
        if (getShippingDate() != null ? !getShippingDate().equals(itemGroup.getShippingDate()) : itemGroup.getShippingDate() != null)
            return false;
        return getCurrentPrice() != null ? getCurrentPrice().equals(itemGroup.getCurrentPrice()) : itemGroup.getCurrentPrice() == null;
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
