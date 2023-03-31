package com.oscarjrod.eurderapi.orders.domain;

import com.oscarjrod.eurderapi.customers.domain.Customer;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Long id;
    @NotNull(message = "Customer cannot be empty!")
    @Valid
    private Customer customer;
    @NotNull(message = "ItemGroups cannot be empty!")
    @Valid
    private List<ItemGroupDto> itemGroups;
    @NotNull(message = "Order date cannot be empty!")
    @PastOrPresent(message = "Order date must be in the past or present!")
    private LocalDate orderDate;
    @NotNull(message = "Total price cannot be empty!")
    @DecimalMin(value = "0.01", message = "Total price must be greater than or at least equal to 0.01!")
    private BigDecimal totalPrice;

    private OrderDto() {
        this.itemGroups = new ArrayList<>();
    }

    public static OrderDto createOrderDto(Long id, Customer customer, List<ItemGroupDto> itemGroups,
                                          LocalDate orderDate, BigDecimal totalPrice) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        orderDto.setCustomer(customer);
        orderDto.setItemGroups(itemGroups);
        orderDto.setOrderDate(orderDate);
        orderDto.setTotalPrice(totalPrice);
        return orderDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ItemGroupDto> getItemGroups() {
        return itemGroups;
    }

    public void setItemGroups(List<ItemGroupDto> itemGroups) {
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
        if (!(o instanceof OrderDto orderDto)) return false;

        if (getId() != null ? !getId().equals(orderDto.getId()) : orderDto.getId() != null) return false;
        if (getCustomer() != null ? !getCustomer().equals(orderDto.getCustomer()) : orderDto.getCustomer() != null)
            return false;
        if (getItemGroups() != null ? !getItemGroups().equals(orderDto.getItemGroups()) : orderDto.getItemGroups() != null)
            return false;
        if (getOrderDate() != null ? !getOrderDate().equals(orderDto.getOrderDate()) : orderDto.getOrderDate() != null)
            return false;
        return getTotalPrice() != null ? getTotalPrice().equals(orderDto.getTotalPrice()) : orderDto.getTotalPrice() == null;
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

