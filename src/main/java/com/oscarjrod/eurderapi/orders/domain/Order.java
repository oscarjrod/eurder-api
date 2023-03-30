package com.oscarjrod.eurderapi.orders.domain;

import com.oscarjrod.eurderapi.customers.domain.Customer;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemGroup> itemGroups;

    private LocalDate orderDate;
    private BigDecimal totalPrice;

    public Order() {
        this.itemGroups = new ArrayList<>();
    }

    public static Order createOrder(Customer customer, List<ItemGroup> itemGroups) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setTotalPrice(calculateOrderTotalPrice(itemGroups));
        order.setItemGroups(itemGroups);
        order.setOrderDate(LocalDate.now());
        return order;
    }

    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ItemGroup> getItemGroups() {
        return itemGroups;
    }

    public void setItemGroups(List<ItemGroup> itemGroups) {
        this.itemGroups = itemGroups;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    private static BigDecimal calculateOrderTotalPrice(List<ItemGroup> itemGroups) {
        return itemGroups.stream()
                .map(ItemGroup::getItemGroupTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void addItemGroup(ItemGroup itemGroup) {
        itemGroup.setOrder(this);
        itemGroups.add(itemGroup);
    }

    public void removeItemGroup(ItemGroup itemGroup) {
        itemGroups.remove(itemGroup);
        itemGroup.setOrder(null);
    }

    @Override
    public String toString() {
        return "Order {" +
                "id = " + id +
                ", customer = " + customer +
                ", itemGroups = " + itemGroups +
                ", orderDate = " + orderDate +
                ", totalPrice = " + totalPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;

        if (getId() != null ? !getId().equals(order.getId()) : order.getId() != null) return false;
        if (getCustomer() != null ? !getCustomer().equals(order.getCustomer()) : order.getCustomer() != null)
            return false;
        if (getItemGroups() != null ? !getItemGroups().equals(order.getItemGroups()) : order.getItemGroups() != null)
            return false;
        if (getOrderDate() != null ? !getOrderDate().equals(order.getOrderDate()) : order.getOrderDate() != null)
            return false;
        return getTotalPrice() != null ? getTotalPrice().equals(order.getTotalPrice()) : order.getTotalPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCustomer() != null ? getCustomer().hashCode() : 0);
        result = 31 * result + (getItemGroups() != null ? getItemGroups().hashCode() : 0);
        result = 31 * result + (getOrderDate() != null ? getOrderDate().hashCode() : 0);
        result = 31 * result + (getTotalPrice() != null ? getTotalPrice().hashCode() : 0);
        return result;
    }

}
